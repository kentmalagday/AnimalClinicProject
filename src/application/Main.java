package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	 private static Stage stg;
	 public Scene scene;
	 
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Database.con = ConDatabase.getConnection();
    	stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
        stg.isAlwaysOnTop();
        
        stg.setOnCloseRequest(new EventHandler<WindowEvent>() {
        	public void handle(WindowEvent e) {
        		try {
        			System.out.println("Closing connection!");
        			Database.con.close();
        		}catch(Exception error) { System.out.println(error);}
        		Platform.exit();
        		System.exit(0);
        	}
        });
        
        
    }
    
    public void changeScene(String fxml, String title, int width, int height) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        scene = new Scene(pane, width,height);
        stg.setTitle(title);
        stg.setScene(scene);
        stg.setResizable(false);
      
    }


    public static void main(String[] args) {
        launch(args);
    }
}
