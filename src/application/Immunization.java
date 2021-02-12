package application;

import java.net.URL;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Immunization implements Initializable{

    @FXML
    Text text_Petname;

    @FXML
    DatePicker picker_Date;

    @FXML
    DatePicker picker_nextDue;

    @FXML
    TextField textField_weight;

    @FXML
    Button btn_back;

    @FXML
    ComboBox<String> combobox_Against;

    @FXML
    ComboBox<String> combobox_Vet;
    
    @FXML
    TableView<PetImmunization> immunizationTable;

    @FXML
    TableColumn<PetImmunization, LocalDate> date;

    @FXML
    TableColumn<PetImmunization, String> weight;

    @FXML
    TableColumn<PetImmunization, String> against;

    @FXML
    TableColumn<PetImmunization, String> veterinarian;

    @FXML
    TableColumn<PetImmunization, LocalDate> nextDue;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Distemper-Hepatitis-Leptospirosis","Leptospirosis","Parainfluenza","Canine Corana Virus",
        "Canine Parvovirus","Feline Distemper", "Feline Distemper-Calici Virus Rhinotracheitis"); 
		combobox_Against.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("Dr. Nielson Donato","Dr. Ashley Abaday","Dr. Eufemia Abance"); 
		combobox_Vet.setItems(list1);
		
		immunizationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		date.setCellValueFactory(new PropertyValueFactory<PetImmunization, LocalDate>("date"));
		weight.setCellValueFactory(new PropertyValueFactory<PetImmunization, String>("weight"));
		against.setCellValueFactory(new PropertyValueFactory<PetImmunization, String>("against"));
		veterinarian.setCellValueFactory(new PropertyValueFactory<PetImmunization, String>("veterinarian"));
		nextDue.setCellValueFactory(new PropertyValueFactory<PetImmunization, LocalDate>("nextDue"));
		
		try {
			immunizationTable.setItems(PetImmunizationList.getPetImmunizationList(Pethealth.selectedPet));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		text_Petname.setText(Pethealth.selectedPet.getAnimalName());
    }

    public void save() throws Exception{
    	System.out.println("Saving!");
    	if (textFieldsEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Pet");
    		alert.setHeaderText("Please fill up all fields!");
    		alert.showAndWait();
		} else {
			PetImmunization petImmunization = new PetImmunization();
			petImmunization.setDate(picker_Date.getValue());
			petImmunization.setWeight(textField_weight.getText());
			petImmunization.setAgainst(combobox_Against.getValue());
			petImmunization.setVeterinarian(combobox_Vet.getValue());
			petImmunization.setNextDue(picker_nextDue.getValue());
			Database.saveImmunization(petImmunization, Pethealth.selectedPet);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Immunization");
			alert.setHeaderText("Pet Immunization Record is added succesfully!");
			alert.showAndWait();
			immunizationTable.setItems(PetImmunizationList.getPetImmunizationList(Pethealth.selectedPet));
			clearFields();
		}
			
    }
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }

    boolean textFieldsEmpty() {
		int i = 0;
		if (picker_Date.getValue() == null) {
			i++;
		}
		if (textField_weight.getText().isEmpty()) {
			i++;
		}
		if (combobox_Against.getValue() == null) {
			i++;
		}
		if (combobox_Vet.getValue() == null) {
			i++;
		}
		if (picker_nextDue.getValue() == null) {
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
    	picker_Date.setValue(null);
    	textField_weight.setText("");
    	combobox_Against.setValue(null);
    	combobox_Vet.setValue(null);
    	picker_nextDue.setValue(null);
    }
}
