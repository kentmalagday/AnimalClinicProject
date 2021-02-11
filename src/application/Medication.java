package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Medication implements Initializable{
	
	@FXML
	Label petname;
	@FXML
	TableView<PetMedication> medicationTable;

    @FXML
    TableColumn<PetMedication, String> description;

    @FXML
    TableColumn<PetMedication, String> dosage;

    @FXML
    TableColumn<PetMedication, String> status;

    @FXML
    TextArea textArea_Description;

    @FXML
    TextField textField_dosage;

    @FXML
    ComboBox<String> comboBox_status;
    
    @FXML
    Button btn_back;

	@FXML
	TextField textField_drugName;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Taken", "Not Taken"); 
		comboBox_status.setItems(list);
		
		medicationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		description.setCellValueFactory(new PropertyValueFactory<PetMedication, String>("description"));
		dosage.setCellValueFactory(new PropertyValueFactory<PetMedication, String>("dosage"));
		status.setCellValueFactory(new PropertyValueFactory<PetMedication, String>("status"));
		
		try {
			medicationTable.setItems(PetMedicationList.getPetMedicationList());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		petname.setText(Pethealth.selectedPet.getAnimalName());
    }
    
    public void save() throws Exception{
    	System.out.println("Saving!");
    	if (textFieldsEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Pet");
    		alert.setHeaderText("Please fill up all fields!");
    		alert.showAndWait();
		} else {
			PetMedication petMedication = new PetMedication();
			petMedication.setDescription(textArea_Description.getText());
			petMedication.setDosage(textField_dosage.getText());
			petMedication.setStatus(comboBox_status.getValue());
			Database.saveMedication(petMedication, Pethealth.selectedPet);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Immunization");
			alert.setHeaderText("Pet Immunization Record is added succesfully!");
			alert.showAndWait();
			medicationTable.setItems(PetMedicationList.getPetMedicationList());
			clearFields();
		}
    }
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }
    boolean textFieldsEmpty() {
		int i = 0;
		if (textArea_Description.getText().isEmpty()) {
			i++;
		}
		if (textField_dosage.getText().isEmpty()) {
			i++;
		}
		if (comboBox_status.getValue() == null) {
			i++;
		}
		if (i > 0) {
			System.out.println("There are empty textfields");
			return true;
		} else {
			System.out.println("No Empty TextFields!");
		}
		return false;
	}
    
    void clearFields() {
    	textArea_Description.setText("");
    	textField_dosage.setText("");
    	comboBox_status.setValue(null);
    }
}