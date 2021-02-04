package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

public class Appointments implements Initializable{

    @FXML
    private TableColumn<Appointment, Integer> id;

    @FXML
    private TableColumn<Appointment, LocalDate> date;

    @FXML
    private TableColumn<Appointment, String> time;
    
    @FXML
    private TableColumn<Appointment, String> clientName;
    
    @FXML
    private TableView<Appointment> appointmentTable;
    
    TableViewSelectionModel<Appointment> selectionModel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
   	 	
   	 	appointmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   	 	id.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("ID"));
   	 	date.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("date"));
   	 	time.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
   	 	clientName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("clientName"));
   	 	try {
	 		appointmentTable.setItems(AppointmentList.getAppointmentList());
	 	}catch(Exception e) {
	 		System.out.println(e);
	 	}
   	 	selectionModel = appointmentTable.getSelectionModel();
   	 	selectionModel.setSelectionMode(SelectionMode.SINGLE);
   	 	
    }
    
    public void back() throws IOException{
    	Main m = new Main();
<<<<<<< HEAD
        m.changeScene("Home.fxml", "Home", 1056, 668);
=======
        m.changeScene("home.fxml", "Home", 1074, 748);
>>>>>>> 6a4c71969a7d1ed9211be0a59cc22486b0810a61
    }
}

