package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.mysql.cj.protocol.Resultset;

public class Database {

    public static void addClient(Client c) throws Exception {
        try {
            Connection con = getConnection();
            String command = "INSERT INTO `mork_petclinic`.`client` (`lastName`, `firstName`, `age`, `contact`, `email`, `address`) VALUES ('"
                    + c.getLastName() + "', '" + c.getFirstName() + "', '" + c.getAge() + "', '" + c.getContact()
                    + "', '" + c.getEmail() + "', '" + c.getAddress() + "');";
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
            System.out.println(command);
            con.close();
        } catch (Exception e) {
         
        }
    }

    public static void addUser(Users u) throws Exception {
        try {
            Connection con = getConnection();
            String command = "INSERT INTO `mork_petclinic`.`users` (`username`, `password`) VALUES ('" + u.getUsername()
                    + "', '" + u.getPassword() + "');";
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
            System.out.println(command);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            
        }
    }

    public static boolean verifyLogin(Users u) throws Exception {
        Connection con;
        ResultSet rs;
        try {
            con = getConnection();
            String command = "SELECT * FROM users WHERE username ='" + u.getUsername() + "'AND password ='" + u.getPassword() + "'";
            PreparedStatement search = con.prepareStatement(command);
            rs = search.executeQuery();
            if(rs.next()){
            	con.close();
                return true;
            }else{
            	con.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    
    public static void addPet(Pet p, Client selected) throws Exception{
        try{
            Connection con = getConnection();
            String command = "INSERT INTO `mork_petclinic`.`animal` (`animal_name`, `species`, `breed`, `weight`, `age`, `color`, `purpose`, `animal_owner_id`, `appointmentDate`, `appointmentTime`) VALUES ('" + p.getAnimalName() + "', '" + p.getSpecies() + 
            		"', '" +p.getBreed() + "', '" + p.getWeight() + "', '" + p.getAge() + "', '" + p.getColor() + "', '" + p.getPurpose()+ "', '" + selected.getID() +"', '"+p.getAppointmentDate()+"', '"+p.getAppointmentTime()+"');";
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
            System.out.println(command);
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void updateClient(Client c, Client selected) throws Exception{
    	try {
    		Connection con = getConnection();
    		String command = "UPDATE `mork_petclinic`.`client` SET `lastName` = '" +c.getLastName() + "', `firstName` = '" +c.getFirstName() + "', `age` = '" +c.getAge() + "', `contact` = '" +c.getContact() + "', `email` = '" +c.getEmail() + "', `address` = '" +c.getAddress()+ "' WHERE `mork_petclinic`.`client`.`ID` = " +selected.getID()+ "";
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    		con.close();
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
   

    public static void updatePet(Pet p, Pet selected) throws Exception{
    	try {
    		Connection con = getConnection();
    		String command = "UPDATE `mork_petclinic`.`animal` SET `animal_name` = '" +p.getAnimalName() + "', `species` = '" +p.getSpecies() + "', `breed` = '" +p.getBreed() + "', `weight` = '" +p.getWeight() + "', `age` = '" +p.getAge() + "', `color` = '" +p.getColor()+ "', `purpose` = '" +p.getPurpose() +"', `appointmentDate` = '" +p.getAppointmentDate() + "', `appointmentTime` = '" +p.getAppointmentTime() +"' WHERE `mork_petclinic`.`animal`.`animal_id` = " +selected.getID()+ "";
    		if(selected.getAppointmentDate() != LocalDate.now() && selected.getAppointmentTime() != "00:00:00") {
    			if(p.getAppointmentDate() == LocalDate.now() || p.getAppointmentTime() == "00:00:00") {
        			System.out.println("Appoinment being deleted!");
        			deleteAppointment(selected.getID());
    			}else {
    				updateAppointment(p, selected);
    			}
    		}else {
        		if(p.getAppointmentDate() != LocalDate.now() && p.getAppointmentTime() != "00:00:00") {
        			addAppointment(selected, new Appointment(0, 0, p.getAppointmentDate(), p.getAppointmentTime(), ""));
        		}
    		}
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    		con.close();
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
    public static void updateAppointment(Pet p, Pet selected) throws Exception{
    	try {
    		Connection con = getConnection();
        	String command = "UPDATE `mork_petclinic`.`appointment` SET `date` = '"+p.getAppointmentDate()+"', `time` = '"+p.getAppointmentTime()+"' WHERE `appointment`.`pet_id` = "+selected.getID()+"";
        	PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    		con.close();
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
    
    public static void deleteAppointment(int petID) {
    	try {
    		Connection con = getConnection();
    		String command = "DELETE FROM `appointment` WHERE `appointment`.`pet_id` = "+petID+";";
    		PreparedStatement delete = con.prepareStatement(command);
    		delete.executeUpdate();
    		System.out.println(command);
    		con.close();
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
    
    public static void addAppointment(Pet p, Appointment a) throws Exception{
    	try {
    		Connection con = getConnection();
    		String first = "SELECT * FROM `mork_petclinic`.`animal` WHERE '"+p.getAnimalName()+"' in (animal_name) AND '"+p.getSpecies()+"' in (species)";
    		ResultSet rs = con.createStatement().executeQuery(first);
    		Pet result = new Pet();
    		while(rs.next()) {
    			result.setID(rs.getInt("animal_id"));
    			result.setAnimalName(rs.getString("animal_name"));
    			result.setSpecies(rs.getString("species"));
    			result.setBreed(rs.getString("breed"));
    			result.setWeight(rs.getFloat("weight"));
    			result.setAge(rs.getInt("age"));
    			result.setColor(rs.getString("color"));
    			result.setPurpose(rs.getString("purpose"));
    			result.setAppointmentDate(rs.getDate("appointmentDate").toLocalDate());
    			result.setAppointmentTime(rs.getString("appointmentTime").toString());
    		}
    		String command = "INSERT INTO `mork_petclinic`.`appointment` (`date`, `time`, `pet_id`) VALUES ('"+sqlDateFormat(a.getDate()) +"', '"+sqlTimeFormat(a.getTime())+"', '"+result.getID()+"')";
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    		con.close();
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
            
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conencted!");
            return conn;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public static Date sqlDateFormat(LocalDate date) {
    	
    
         Date sqlDate = Date.valueOf(date);
         System.out.println(sqlDate);
         return sqlDate;
            
    	
    }
    public static Time sqlTimeFormat(String time) {
    	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    	try {
            java.util.Date utilTime = format.parse(time);
            java.sql.Time sqlTime = new Time(utilTime.getTime());
            System.out.println(sqlTime);
            return sqlTime;
            
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    	
    }
 
}
