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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Grooming implements Initializable {

    @FXML
    CheckBox checkbox_teethBrushing;

    @FXML
    CheckBox checkbox_deMattingtreatment;

    @FXML
    CheckBox checkbox_deTickingtreatment;

    @FXML
    CheckBox checkbox_nailCLipping;

    @FXML
    CheckBox checkbox_earClipping;

    @FXML
    CheckBox checkbox_pawPadCleaning;

    @FXML
    Text text_Petname;

    @FXML
    DatePicker picker_date;

    @FXML
    ComboBox<String> combobox_typeOfgroom;

    @FXML
    ComboBox<String> combobox_sizeOfpet;

    @FXML
    TextField textField_groomer;

    @FXML
    TableColumn<PetGrooming, LocalDate> date;

    @FXML
    TableColumn<PetGrooming, String> groom;

    //@FXML
    //TableColumn<> sizeofpet;

    @FXML
    TableColumn<PetGrooming, String> treatments;

    @FXML
    TableColumn<PetGrooming, String> groomer;
    
    @FXML
    TableView<PetGrooming> groomingTable;

    @FXML
    Button btn_back;
    @FXML
    Text text_size;

    @Override
    public void initialize(URL location, ResourceBundle resources){
//        ObservableList<String> list = FXCollections.observableArrayList("Small (0-5lb)", "Medium (6-20lb)","Large (21-40lb)",
//        "Extra large (41-60lb)","Double XL (61lb or higher)"); 
//		combobox_sizeOfpet.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("Basic Groom","Full Groom","Bath & Dry","None");
        combobox_typeOfgroom.setItems(list1);
        
        groomingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		date.setCellValueFactory(new PropertyValueFactory<PetGrooming, LocalDate>("date"));
		groom.setCellValueFactory(new PropertyValueFactory<PetGrooming, String>("groom"));
		treatments.setCellValueFactory(new PropertyValueFactory<PetGrooming, String>("treatments"));
		groomer.setCellValueFactory(new PropertyValueFactory<PetGrooming, String>("groomer"));
		
		try {
			groomingTable.setItems(PetGrooming.getPetGroomingList(Pethealth.selectedPet));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		float pounds = Pethealth.selectedPet.getWeight() * 2.20462262185f;
		
		if(pounds >= 0 && pounds <= 5) {
			text_size.setText("Small " + pounds + "lbs");
		}else if(pounds > 5 && pounds <= 20) {
			text_size.setText("Medium " + pounds + "lbs");
		}else if(pounds > 20 && pounds <= 40) {
			text_size.setText("Large " + pounds + "lbs");
		}else if(pounds > 40 && pounds <= 60) {
			text_size.setText("Extra Large " + pounds + "lbs");
		}else if(pounds > 60) {
			text_size.setText("XXL " + pounds + "lbs");
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
			PetGrooming petGrooming = new PetGrooming();
			petGrooming.setDate(picker_date.getValue());
			petGrooming.setGroom(combobox_typeOfgroom.getValue());
			String treatments = "(CONCAT_WS(CHAR(10 using utf8), ";
		
			if (checkbox_teethBrushing.isSelected()) {
				treatments = treatments + "'" + checkbox_teethBrushing.getText() + "',";
			}
			if (checkbox_deMattingtreatment.isSelected()) {
				treatments = treatments + "'" + checkbox_deMattingtreatment.getText() + "',";
			}
			if (checkbox_deTickingtreatment.isSelected()) {
				treatments = treatments + "'" + checkbox_deTickingtreatment.getText() + "',";
			}
			if (checkbox_nailCLipping.isSelected()) {
				treatments = treatments + "'" + checkbox_nailCLipping.getText() + "',";
			}
			if (checkbox_earClipping.isSelected()) {
				treatments = treatments + "'" + checkbox_earClipping.getText() + "',";
			}
			if (checkbox_pawPadCleaning.isSelected()) {
				treatments = treatments + "'" + checkbox_pawPadCleaning.getText() + "',";
			}
			
			treatments = treatments + "''))";
			
			petGrooming.setTreatments(treatments);
			petGrooming.setGroomer(textField_groomer.getText());
			Database.saveGrooming(petGrooming, Pethealth.selectedPet);
			System.out.println(treatments);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Grooming");
			alert.setHeaderText("Pet Grooming Record is added succesfully!");
			alert.showAndWait();
			groomingTable.setItems(PetGrooming.getPetGroomingList(Pethealth.selectedPet));
			clearFields();
		}
			
    }
    
    boolean textFieldsEmpty() {
		int i = 0;
		if (picker_date.getValue() == null) {
			i++;
		}
		if (combobox_typeOfgroom.getValue() == null) {
			i++;
		}
		
		if (textField_groomer.getText().isEmpty()) {
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
    	picker_date.setValue(null);
    	combobox_typeOfgroom.setValue(null);
    	textField_groomer.setText("");
    	checkbox_teethBrushing.setSelected(false);
        checkbox_deMattingtreatment.setSelected(false);
        checkbox_deTickingtreatment.setSelected(false);
        checkbox_nailCLipping.setSelected(false);
        checkbox_earClipping.setSelected(false);
        checkbox_pawPadCleaning.setSelected(false);
    }
    
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }
}
