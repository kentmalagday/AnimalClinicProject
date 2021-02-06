package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AddPet implements Initializable{
    @FXML
    Button btn_back;
    @FXML
    Button btn_savePet;
    @FXML
    Button btn_deletePet;
    @FXML
    TextField textField_petName;
    @FXML
    TextField textField_species;
    @FXML
    TextField textField_breed;
    @FXML
    TextField textField_weight;
    @FXML
    TextField textField_age;
    @FXML
    TextField textField_color;
    @FXML
    TextArea textArea_purposeOfVisit;
    @FXML
    DatePicker picker_appointmentDate;
    @FXML
    TextField textField_time;
    
    @FXML
    TableView<Pet> petTable;
    @FXML
    TableColumn<Pet, Integer> animal_id;
    @FXML
    TableColumn<Pet, String> name;
    @FXML
    TableColumn<Pet, String> species;
    @FXML
    TableColumn<Pet, String> breed;
    @FXML
    TableColumn<Pet, Float> weight;
    @FXML
    TableColumn<Pet, Integer> age;
    @FXML
    TableColumn<Pet, String> color;
    @FXML
	TableColumn<Pet, String> purpose;
    @FXML
	TableColumn<Pet, LocalDate> appointmentDate;
    @FXML
	TableColumn<Pet, String> appointmentTime;
    
    TableViewSelectionModel<Pet> selectionModel;
    Pet selectedPet;
    
    TextField[] emptyTextFields = new TextField[10];
    public static Client selectedClient;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
   	 	
   	 	petTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   	 	
   	 	name.setCellValueFactory(new PropertyValueFactory<Pet, String>("animalName"));
   	 	species.setCellValueFactory(new PropertyValueFactory<Pet, String>("species"));
   	 	breed.setCellValueFactory(new PropertyValueFactory<Pet, String>("breed"));
   	 	weight.setCellValueFactory(new PropertyValueFactory<Pet, Float>("weight"));
   	 	age.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("age"));
   	 	color.setCellValueFactory(new PropertyValueFactory<Pet, String>("color"));
   	 	purpose.setCellValueFactory(new PropertyValueFactory<Pet, String>("purpose"));
   	 	animal_id.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("ID"));
   	 	appointmentDate.setCellValueFactory(new PropertyValueFactory<Pet, LocalDate>("appointmentDate"));
   	 	appointmentTime.setCellValueFactory(new PropertyValueFactory<Pet, String>("appointmentTime"));
   	 
   	 	
   	 	try {
	 		petTable.setItems(PetList.getPetList(selectedClient));
	 	
	 	}catch(Exception e) {
	 		System.out.println(e);
	 	}
   	 	selectionModel = petTable.getSelectionModel();
   	 	selectionModel.setSelectionMode(SelectionMode.SINGLE);
   	 	
   	 	
    }
    public void addPet() throws Exception{
    	if(textFieldsEmpty()) {
    		for(int i = 0; i < emptyTextFields.length; i++) {
    			if(emptyTextFields[i] == null) {
    				break;
    			}
    			System.out.println(emptyTextFields[i]);
    			emptyTextFields[i].setText("Please input something before saving!");
    		}
    	}else {
    		if(!checkIfValid()) {
    			return;
    		}
    		Pet pet = new Pet();
    		Appointment appointment = new Appointment();
        	pet.setAnimalName(textField_petName.getText());
        	pet.setSpecies(textField_species.getText());
        	pet.setBreed(textField_breed.getText());
        	pet.setWeight(Float.parseFloat(textField_weight.getText()));
        	pet.setAge(Integer.parseInt(textField_age.getText()));
        	pet.setColor(textField_color.getText());
			pet.setPurpose(textArea_purposeOfVisit.getText());
			
			if(picker_appointmentDate.getValue() != null) {
				pet.setAppointmentDate(picker_appointmentDate.getValue());
				pet.setAppointmentTime(textField_time.getText());
				appointment.setDate(pet.getAppointmentDate());
				appointment.setTime(pet.getAppointmentTime());
			} else {

				pet.setAppointmentTime("00:00:00");
				System.out.println("null date");
				appointment = null;
			}

			if (petTable.getSelectionModel().isEmpty()) {
				Database.addPet(pet, selectedClient);
				if (appointment != null) {
					Database.addAppointment(pet, appointment);
				}
				try {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Pet");
					alert.setHeaderText("Pet record is added succesfully!");
					alert.showAndWait();
					petTable.setItems(PetList.getPetList(selectedClient));
					clearTextFields();
				} catch (Exception error) {
					System.out.println(error);
				}
			} else {
				try {
					Database.updatePet(pet, selectedPet);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Pet");
					alert.setHeaderText("Pet record has been updated!");
					alert.showAndWait();
					petTable.setItems(PetList.getPetList(selectedClient));
					clearTextFields();
				} catch (Exception error) {
					System.out.println(error);
				}
			}
		}
	}

	public void deletePet() {
		if (petTable.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("No pet selected.");
			alert.showAndWait();
		} else {
			Alert check = new Alert(AlertType.CONFIRMATION);
			check.setTitle("Delete Client");
			check.setHeaderText(null);
			check.setContentText("Are you sure you want to delete this client data?");
			Optional<ButtonType> result = check.showAndWait();
			if (result.get() == ButtonType.OK) { // Tinanggal ko yung pet na parameter sa deletePet kasi di naagamit sa
													// function

				Database.deletePet(selectedPet);
				try {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Pet");
					alert.setHeaderText("Pet record has been deleted!");
					alert.showAndWait();
					petTable.setItems(PetList.getPetList(selectedClient));
					clearTextFields();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public void getSelection(MouseEvent e) {
		Node source = e.getPickResult().getIntersectedNode();
		if (selectionModel.getSelectedItem() != null) {
			selectedPet = selectionModel.getSelectedItem();
			textField_petName.setText(selectedPet.getAnimalName());
			textField_species.setText(selectedPet.getSpecies());
			textField_breed.setText(selectedPet.getBreed());
			textField_weight.setText(Float.toString(selectedPet.getWeight()));
			textField_age.setText(Integer.toString(selectedPet.getAge()));
			textField_color.setText(selectedPet.getColor());
			textArea_purposeOfVisit.setText(selectedPet.getPurpose());
		}
		while (source != null && !(source instanceof TableRow)) {
			source = source.getParent();
		}

		if (source == null || (source instanceof TableRow && ((TableRow) source).isEmpty())) {
			petTable.getSelectionModel().clearSelection();
			clearTextFields();
		}
		System.out.println("Clicked!");
	}

	public void clearTextFields() {
		textField_petName.setText("");
		textField_species.setText("");
		textField_breed.setText("");
		textField_weight.setText("");
		textField_age.setText("");
		textField_color.setText("");
		textArea_purposeOfVisit.setText("");
		textField_time.setText("");
		picker_appointmentDate.setValue(null);
	}

	public boolean textFieldsEmpty() {
		int i = 0;
		if (textField_petName.getText().isEmpty()) {
			System.out.println("EMPTY");
			emptyTextFields[i] = textField_petName;
			i++;
		}
		if (textField_species.getText().isEmpty()) {
			emptyTextFields[i] = textField_species;
			i++;
		}
		if (textField_breed.getText().isEmpty()) {
			emptyTextFields[i] = textField_breed;
			i++;
		}
		if (textField_weight.getText().isEmpty()) {
			emptyTextFields[i] = textField_weight;
			i++;
		}
		if (textField_age.getText().isEmpty()) {
			emptyTextFields[i] = textField_age;
			i++;
		}
		if (textField_color.getText().isEmpty()) {
			emptyTextFields[i] = textField_color;
			i++;
		}

		if (i > 0) {
			System.out.println("There are empty textfields");

			return true;
		} else {
			System.out.println("No Empty TextFields!");
			Arrays.fill(emptyTextFields, null);
		}
		return false;
	}

	public boolean checkIfValid() {
		boolean yes = false;
		try {
			Float.parseFloat(textField_weight.getText());
			yes = true;
		} catch (Exception e) {
			System.out.println("Weight should be a number");
			textField_weight.setText("Invalid!");
			return false;
		}
		try {
			Integer.parseInt(textField_age.getText());
			yes = true;
		} catch (Exception e) {
			System.out.println("Age not valid!");
			textField_age.setText("Invalid!");
			return false;
		}
		if (!textField_time.getText().isEmpty()) {
			if (textField_time.getText().matches("\\d{2}:\\d{2}$")
					|| textField_time.getText().matches("//d{2}://d{2}://d{2}")) {
				yes = true;
			} else {
				System.out.println("Time should be in the formant: [HH:mm] or [HH:mm:SS]");
				textField_time.setText("HH:mm");
        		return false;
        	}
    	}
    	return yes;
    }
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("clientforms.fxml", "Clients", 1074, 748);
    }
}
