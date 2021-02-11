package application;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Platform;

public class GetConnection {
	public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://mork.heliohost.us:3306/mork_petclinic";
            String username = "mork_lance";
            String password = "lance";
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
