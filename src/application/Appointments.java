package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private TableColumn<Appointment, String> petName;
    
    @FXML
    private TableView<Appointment> appointmentTable;
    
    TableViewSelectionModel<Appointment> selectionModel;
    Appointment selectedAppointment;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
   	 	
   	 	appointmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   	 	id.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("ID"));
   	 	date.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("date"));
   	 	time.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
   	 	clientName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("clientName"));
   	 	petName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("petName"));
   	 	try {
	 		appointmentTable.setItems(AppointmentList.getAppointmentList());
	 	}catch(Exception e) {
	 		System.out.println(e);
	 	}
   	 	selectionModel = appointmentTable.getSelectionModel();
   	 	selectionModel.setSelectionMode(SelectionMode.SINGLE);
   	 	
    }
    
    public void getSelection(MouseEvent e) {
		Node source = e.getPickResult().getIntersectedNode();
		if (selectionModel.getSelectedItem() != null) {
			selectedAppointment = selectionModel.getSelectedItem();
		}
		while (source != null && !(source instanceof TableRow)) {
			source = source.getParent();
		}

		if (source == null || (source instanceof TableRow && ((TableRow) source).isEmpty())) {
			appointmentTable.getSelectionModel().clearSelection();
			selectedAppointment = null;
			
		}
		System.out.println("Clicked!");
	}
    
    public void back() throws IOException{
    	Main m = new Main();
        m.changeScene("Home.fxml", "Home", 1056, 668);
    }
    
    public void delete() throws Exception{
    	if (appointmentTable.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("No appointment selected.");
			alert.showAndWait();
		} else {
			Alert check = new Alert(AlertType.CONFIRMATION);
			check.setTitle("Appointment Done");
			check.setHeaderText(null);
			check.setContentText("Are you sure that this appointment is done?");
			Optional<ButtonType> result = check.showAndWait();
			if (result.get() == ButtonType.OK) {
				Database.deleteAppointment(selectedAppointment.getID());
				Database.deletePetAppointment(selectedAppointment.getPetID());
				try {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Appointment");
					alert.setHeaderText("Appointment Done!");
					alert.showAndWait();
					appointmentTable.setItems(AppointmentList.getAppointmentList());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
    }
}
    

