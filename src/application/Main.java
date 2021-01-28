package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	 private static Stage stg;
	 public Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        scene = new Scene(pane, 1050, 650);
        stg.setTitle("Home");
        stg.setScene(scene);
        stg.setResizable(false);
      
    }


    public static void main(String[] args) {
        launch(args);
    }
}
