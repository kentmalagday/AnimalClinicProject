package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import javafx.application.Platform;

public class Database {
	public static Connection con;
	
    public static void addClient(Client c) throws Exception {
        try {
            String command = "INSERT INTO `mork_petclinic`.`client` (`lastName`, `firstName`, `age`, `contact`, `email`, `address`) VALUES ('"
                    + c.getLastName() + "', '" + c.getFirstName() + "', '" + c.getAge() + "', '" + c.getContact()
                    + "', '" + c.getEmail() + "', '" + c.getAddress() + "');";
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
            System.out.println(command);
        } catch (Exception e) {
        	
        }
    }

    public static void addUser(Users u) throws Exception {
        try {
            String command = "INSERT INTO `mork_petclinic`.`users` (`username`, `password`) VALUES ('" + u.getUsername()
                    + "', '" + u.getPassword() + "');";
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
            System.out.println(command);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean verifyLogin(Users u) throws Exception {
        ResultSet rs;
        try {
            String command = "SELECT * FROM users WHERE username ='" + u.getUsername() + "'AND password ='" + u.getPassword() + "'";
            PreparedStatement search = con.prepareStatement(command);
            rs = search.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            con = getConnection();
        }
        return false;

    }
    
    public static void addPet(Pet p, Client selected) throws Exception{
        try{
            String command= "INSERT INTO `mork_petclinic`.`animal` (`animal_name`, `species`, `breed`, `weight`, `age`, `color`, `sex`, `purpose`, `animal_owner_id`, `appointmentDate`, `appointmentTime`) VALUES ('" + p.getAnimalName() + "', '" + p.getSpecies() + 
            		"', '" +p.getBreed() + "', '" + p.getWeight() + "', '" + p.getAge() + "', '" + p.getColor() + "', '" + p.getSex() + "', '" + p.getPurpose()+ "', '" + selected.getID() +"', '"+p.getAppointmentDate()+"', '"+p.getAppointmentTime()+"');";
            if(p.getAppointmentDate() == null || p.getAppointmentTime() == "") {
            	command = "INSERT INTO `mork_petclinic`.`animal` (`animal_name`, `species`, `breed`, `weight`, `age`, `color`, `sex`, `purpose`, `animal_owner_id`, `appointmentDate`, `appointmentTime`) VALUES ('" + p.getAnimalName() + "', '" + p.getSpecies() + 
                		"', '" +p.getBreed() + "', '" + p.getWeight() + "', '" + p.getAge() + "', '" + p.getColor() + "', '" + p.getSex() + "', '" + p.getPurpose()+ "', '" + selected.getID() +"', NULL, NULL);";
            }
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
            System.out.println(command);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void updateClient(Client c, Client selected) throws Exception{
    	try {
    		String command = "UPDATE `mork_petclinic`.`client` SET `lastName` = '" +c.getLastName() + "', `firstName` = '" +c.getFirstName() + "', `age` = '" +c.getAge() + "', `contact` = '" +c.getContact() + "', `email` = '" +c.getEmail() + "', `address` = '" +c.getAddress()+ "' WHERE `mork_petclinic`.`client`.`ID` = " +selected.getID()+ "";
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
   

    public static void updatePet(Client c, Pet p, Pet selected) throws Exception{
    	try {
    		String command = "";
    		if(selected.getAppointmentDate() != null && selected.getAppointmentTime() != "") {
    			if(p.getAppointmentDate() == null || p.getAppointmentTime() == "") {
        			System.out.println("Appoinment being deleted!");
        			command = "UPDATE `mork_petclinic`.`animal` SET `animal_name` = '" +p.getAnimalName() + "', `species` = '" +p.getSpecies() + "', `breed` = '" +p.getBreed() + "', `weight` = '" +p.getWeight() + "', `age` = '" +p.getAge() + "', `color` = '" +p.getColor()+ "', `sex` = '" +p.getSex() +"', `purpose` = '" +p.getPurpose() +"', `appointmentDate` = NULL, `appointmentTime` = NULL WHERE `mork_petclinic`.`animal`.`animal_id` = " +selected.getID()+ "";
        			deleteAppointment(selected.getID());
    			}else {
    				command = "UPDATE `mork_petclinic`.`animal` SET `animal_name` = '" +p.getAnimalName() + "', `species` = '" +p.getSpecies() + "', `breed` = '" +p.getBreed() + "', `weight` = '" +p.getWeight() + "', `age` = '" +p.getAge() + "', `color` = '" +p.getColor()+ "', `sex` = '" +p.getSex() +"', `purpose` = '" +p.getPurpose() +"', `appointmentDate` = '"+p.getAppointmentDate()+"', `appointmentTime` = '"+p.getAppointmentTime()+"' WHERE `mork_petclinic`.`animal`.`animal_id` = " +selected.getID()+ "";
    				updateAppointment(p, selected);
    			}
    		}else {
        		if(p.getAppointmentDate() != LocalDate.now() && p.getAppointmentTime() != "") {
        			addAppointment(c, selected, new Appointment(0, 0, p.getAppointmentDate(), p.getAppointmentTime(), "", ""));
        		}
    		}
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
    
    public static void updateAppointment(Pet p, Pet selected) throws Exception{
    	try {
        	String command = "UPDATE `mork_petclinic`.`appointment` SET `date` = '"+p.getAppointmentDate()+"', `time` = '"+p.getAppointmentTime()+"' WHERE `appointment`.`pet_id` = "+selected.getID()+"";
        	PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
    
    public static void deletePet(Pet selected){
        try {
            
            //String command = "DELETE FROM `mork_petclinic`.`animal` WHERE `mork_petclinic`.`animal`.`animal_id` = " +selected.getID()+ "";
            String command = "UPDATE `mork_petclinic`.`animal` SET `deleted` = '1' WHERE `animal`.`animal_id` = "+selected.getID()+"";
        	deleteAppointment(selected.getID());
            PreparedStatement delete = con.prepareStatement(command);
            delete.executeUpdate();
            System.out.println(command);
            
		} catch (Exception e) {
			e.printStackTrace();
        }
    }

    public static void deleteClient(Client c, Client selected){
        try {
            String find = "SELECT `animal_id` FROM `animal` WHERE `animal`.`animal_owner_id` = "+selected.getID()+";";
            PreparedStatement search = con.prepareStatement(find);
            ResultSet rs = search.executeQuery();
            while(rs.next()){
                int pet_id = rs.getInt("animal_id");
                deleteAppointment(pet_id);
                //String command_animal = "DELETE FROM `animal` WHERE `animal`.`animal_owner_id` = "+selected.getID()+";";
                String command_animal = "UPDATE `mork_petclinic`.`animal` SET `deleted` = '1', `appointmentDate` = NULL, `appointmentTime` = NULL WHERE `animal`.`animal_owner_id` = "+selected.getID()+"";
                PreparedStatement delete_animal = con.prepareStatement(command_animal);
                delete_animal.executeUpdate();
                System.out.println(command_animal);
            }
            //String command = "DELETE FROM `mork_petclinic`.`client` WHERE `mork_petclinic`.`client`.`ID` = " +selected.getID()+ "";
            String command = "UPDATE `mork_petclinic`.`client` SET `deleted` = '1' WHERE `mork_petclinic`.`client`.`ID` = " +selected.getID()+ "";
            PreparedStatement delete = con.prepareStatement(command);
            delete.executeUpdate();
            System.out.println(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }



    public static void deleteAppointment(int petID) {
    	try {
    		String command = "DELETE FROM `appointment` WHERE `appointment`.`pet_id` = "+petID+";";
    		PreparedStatement delete = con.prepareStatement(command);
    		delete.executeUpdate();
    		System.out.println(command);
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
    
    public static void addAppointment(Client c, Pet p, Appointment a) throws Exception{
    	try {
    		String first = "SELECT * FROM `mork_petclinic`.`animal` WHERE '"+p.getAnimalName()+"' in (animal_name) AND '"+p.getSpecies()+"' in (species) AND '"+p.getBreed()+"' in (breed) AND '"+p.getAge()+"' in (age) AND '"+p.getColor()+"' in (color) AND '"+p.getPurpose()+"' in (purpose) AND '"+p.getAppointmentDate()+"' in (appointmentDate) AND '"+p.getAppointmentTime()+"' in (appointmentTime) AND '"+c.getID()+"' in (animal_owner_id)";
    		System.out.println(first);
    		ResultSet rs = con.createStatement().executeQuery(first);
    		Pet result = new Pet();
    		while(rs.next()) {
    			System.out.println(rs.getString("animal_name"));
    			result.setID(rs.getInt("animal_id"));
    		}
    		String command = "INSERT INTO `mork_petclinic`.`appointment` (`date`, `time`, `pet_id`) VALUES ('"+a.getDate() +"', '"+a.getTime()+"', '"+result.getID()+"')";
    		System.out.println(command);
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://mork.heliohost.us:3306/mork_petclinic";
            String username = "mork_remote";
            String password = "remote";
            Class.forName(driver);
            System.out.println("Connecting! Please Wait.");
            
            Connection conn = DriverManager.getConnection(url, username, password);
            while(conn == null) {
        		System.out.println(".");
        	}
            System.out.println("Connected!");
            return conn;
        }
        catch(Exception e){
        	System.out.println(e);
            System.out.println("Please try opening the program again. Check your Internet Connection.");
            Platform.exit();
            System.exit(0);
        }
        return null;
    }
    

}
    