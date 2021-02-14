package application;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Platform;

public class GetConnection {
	public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://mork.heliohost.us:3306/mork_petclinic";
            String username = "mork_stephen";
            String password = "stephen";
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
            System.out.println("Failed Connection 1.");
            return getConnection1();
            
        }
    }
	public static Connection getConnection1() throws Exception{
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
            System.out.println("Failed Connection 2.");
            return getConnection2();
            
        }
    }
	public static Connection getConnection2() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://mork.heliohost.us:3306/mork_petclinic";
            String username = "mork_genina";
            String password = "genina";
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
            System.out.println("Failed Connection 3.");
            return getConnection3();
        }
    }
	public static Connection getConnection3() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://mork.heliohost.us:3306/mork_petclinic";
            String username = "mork_jared";
            String password = "jared";
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
            System.out.println("Failed Connection 4.");
            return getConnection4();
        }
    }
	public static Connection getConnection4() throws Exception{
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
            System.out.println("Failed Connection 5. Shutting down...");
            Platform.exit();
            System.exit(0);
            
        }
        return null;
    }
}
