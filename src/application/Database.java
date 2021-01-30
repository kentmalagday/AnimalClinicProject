package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        } catch (Exception e) {
            System.out.println(e);
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
                return true;
            }else{
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
            String command = "INSERT INTO `mork_petclinic`.`animal` (`animal_name`, `species`, `breed`, `weight`, `age`, `color`, `purpose`, `animal_owner_id`) VALUES ('" + p.getAnimalName() + "', '" + p.getSpecies() + 
            		"', '" +p.getBreed() + "', '" + p.getWeight() + "', '" + p.getAge() + "', '" + p.getColor() + "', '" + p.getPurpose()+ "', '" + selected.getID() +"');";
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
    		Connection con = getConnection();
    		String command = "UPDATE `mork_petclinic`.`client` SET `lastName` = '" +c.getLastName() + "', `firstName` = '" +c.getFirstName() + "', `age` = '" +c.getAge() + "', `contact` = '" +c.getContact() + "', `email` = '" +c.getEmail() + "', `address` = '" +c.getAddress()+ "' WHERE `mork_petclinic`.`client`.`ID` = " +selected.getID()+ "";
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    }
   

    public static void updatePet(Pet p, Pet selected) throws Exception{
    	try {
    		Connection con = getConnection();
    		String command = "UPDATE `mork_petclinic`.`animal` SET `animal_name` = '" +p.getAnimalName() + "', `species` = '" +p.getSpecies() + "', `breed` = '" +p.getBreed() + "', `weight` = '" +p.getWeight() + "', `age` = '" +p.getAge() + "', `color` = '" +p.getColor()+ "', `purpose` = '" +p.getPurpose()+ "' WHERE `mork_petclinic`.`animal`.`animal_id` = " +selected.getID()+ "";
    		PreparedStatement update = con.prepareStatement(command);
    		update.executeUpdate();
    		System.out.println(command);
    	}catch(Exception error) {
    		System.out.println(error);
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
}
