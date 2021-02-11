package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class Pethealth implements Initializable{
    @FXML
    private Button btn_medical;

    @FXML
    private Button btn_immunization;

    @FXML
    private Button btn_vetvisit;

    @FXML
    private Button btn_medication;

    @FXML
    private Button btn_grooming;

    @FXML
    private Button btn_allergies;

    @FXML
    private TextArea purpose;
    
    public static Pet selectedPet;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		purpose.setText(selectedPet.getPurpose());
	}
    
    public void save() throws Exception{
    	Pet pet = selectedPet;
    	pet.setPurpose(purpose.getText());
    	Database.updatePet(AddPet.selectedClient, pet, selectedPet);
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pet");
		alert.setHeaderText(selectedPet.getAnimalName()+"'s purpose of visit has been updated!");
		alert.showAndWait();
    }
    public void immunization() throws IOException {
        Main m = new Main();
        m.changeScene("immunization.fxml", "Immunization", 1072, 735);

    }

    public void medication() throws IOException {
        Main m = new Main();
        m.changeScene("medication.fxml", "Medication", 1072, 735);
    }

	public void grooming() throws IOException {
        Main m = new Main();
        m.changeScene("grooming.fxml", "Grooming", 1072, 735);
    }

   /* public void vetvisit() throws IOException {
        Main m = new Main();
        m.changeScene("vetvisit.fxml", "Vet Visit", 1072, 735);
    }

    public void allergies() throws IOException {
        Main m = new Main();
        m.changeScene("allergies.fxml", "Allergies", 1072, 735);
    }*/ // for switching scenes kapag may mga fxml na iba na
   
    public void btn_backClicked() throws IOException {
    	selectedPet = null;
        Main m = new Main();
        m.changeScene("addpet.fxml", "Pet", 1522, 839);
    }
}