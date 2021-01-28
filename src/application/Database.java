package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {

    public static void addClient(Client c) throws Exception{
        try{
            Connection con = getConnection();
            String command = "INSERT INTO `mork_petclinic`.`client` (`lastName`, `firstName`, `age`, `contact`, `email`, `address`) VALUES ('" + c.getLastName() + "', '" + c.getFirstName() + 
            		"', '" + c.getAge() + "', '" + c.getContact() + "', '" + c.getEmail() + "', '" + c.getAddress() +"');";
            PreparedStatement add = con.prepareStatement(command);
            add.executeUpdate();
        }
        catch(Exception e){
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
}
