package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Allergies implements Initializable {

    @FXML
    Text text_Petname;

    @FXML
    TextArea textArea_allergy;

    @FXML
    TextArea textArea_description;

    @FXML
    TableView<PetAllergy> allergyTable;

    @FXML
    TableColumn<PetAllergy, String> allergy_name;

    @FXML
    TableColumn<PetAllergy, String> description;

    @FXML
    Button btn_back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allergyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        allergy_name.setCellValueFactory(new PropertyValueFactory<PetAllergy, String>("allergyName"));
        description.setCellValueFactory(new PropertyValueFactory<PetAllergy, String>("description"));

        try {
            allergyTable.setItems(PetAllergiesList.getPetAllergiesList(Pethealth.selectedPet));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        text_Petname.setText(Pethealth.selectedPet.getAnimalName());
    }

    public void save() throws Exception {
        System.out.println("Saving Allergies Info.");
        if(textFieldsEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Pet");
    		alert.setHeaderText("Please fill up all fields!");
    		alert.showAndWait();
        }else{
            PetAllergy petAllergy = new PetAllergy();
            petAllergy.setAllergyName(textArea_allergy.getText());
            petAllergy.setDescription(textArea_description.getText());
            Database.saveAllergy(petAllergy, Pethealth.selectedPet);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Pet Allergies");
            alert.setHeaderText("Pet Allergies Record is added successfully!");
            alert.showAndWait();
            allergyTable.setItems(PetAllergiesList.getPetAllergiesList(Pethealth.selectedPet));
            clearFields();
        }
        
    }


    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }

    boolean textFieldsEmpty(){
        int i = 0;
        if(textArea_allergy.getText().isEmpty()){
            i++;
        }
        if(textArea_description.getText().isEmpty()){
            i++;
        }
        if(i>0){
            System.out.println("There are empty textfields");
            return true;
        }else{
            System.out.println("No empty textfields");
        }
        return false;
    }

    void clearFields(){
        textArea_allergy.setText("");
        textArea_description.setText("");
    }

}

