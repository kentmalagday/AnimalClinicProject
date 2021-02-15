package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Vetvisit implements Initializable {

    @FXML
    Button btn_back;

    @FXML
    DatePicker picker_Date;

    @FXML
    TextArea textArea_Description;

    @FXML
    TextField textField_Veterinarian;

    @FXML
    TextArea textArea_Diagnosis;

    @FXML
    TextArea textArea_Testperformed;

    @FXML
    TextArea textArea_Testresults;

    @FXML
    TextArea textArea_Prescribedactions;

    @FXML
    TextArea textArea_Prescribedmeds;

    @FXML
    TextArea textArea_Addnotes;

    @FXML
    TableView<PetVetVisit> vetvisitTable;

    @FXML
    TableColumn<PetVetVisit, LocalDate> date;

    @FXML
    TableColumn<PetVetVisit, String> description;

    @FXML
    TableColumn<PetVetVisit, String> veterinarian;

    @FXML
    TableColumn<PetVetVisit, String> diagnosis;

    @FXML
    TableColumn<PetVetVisit, String> testperformed;

    @FXML
    TableColumn<PetVetVisit, String> testresults;

    @FXML
    TableColumn<PetVetVisit, String> prescribedactions;

    @FXML
    TableColumn<PetVetVisit, String> prescribedmeds;

    @FXML
    TableColumn<PetVetVisit, String> additionalnotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setCellValueFactory(new PropertyValueFactory<PetVetVisit, LocalDate>("date"));
        description.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("description"));
        veterinarian.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("veterinarian"));
        diagnosis.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("diagnosis"));
        testperformed.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("testPerformed"));
        testresults.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("testResults"));
        prescribedactions.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("prescribedActions"));
        prescribedmeds.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("prescribedMeds"));
        additionalnotes.setCellValueFactory(new PropertyValueFactory<PetVetVisit, String>("additionalNotes"));

        try {
            vetvisitTable.setItems(PetVetVisitList.getPetVetVisitList(Pethealth.selectedPet));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void save() throws Exception {
        System.out.println("Saving!");
        if(textFieldsEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Pet");
    		alert.setHeaderText("Please fill up all fields!");
    		alert.showAndWait();
        }else{
            PetVetVisit petVetVisit = new PetVetVisit();
            petVetVisit.setDate(picker_Date.getValue());
            petVetVisit.setDescription(textArea_Description.getText());
            petVetVisit.setVeterinarian(textField_Veterinarian.getText());
            petVetVisit.setDiagnosis(textArea_Diagnosis.getText());
            petVetVisit.setTestPerformed(textArea_Testperformed.getText());
            petVetVisit.setTestResults(textArea_Testresults.getText());
            petVetVisit.setPrescribedActions(textArea_Prescribedactions.getText());
            petVetVisit.setPrescribedMeds(textArea_Prescribedmeds.getText());
            petVetVisit.setAdditionalNotes(textArea_Addnotes.getText());
            Database.saveVetVisit(petVetVisit, Pethealth.selectedPet);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Vet Visit");
			alert.setHeaderText("Veterinarian Visit Record is added succesfully!");
			alert.showAndWait();
            vetvisitTable.setItems(PetVetVisitList.getPetVetVisitList(Pethealth.selectedPet));
        }
    }

    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }

    boolean textFieldsEmpty(){
        int i = 0;
        if(picker_Date.getValue() == null)
            i++;
        if(textArea_Description.getText().isEmpty())
            i++;
        if(textField_Veterinarian.getText().isEmpty())
            i++;
        if(textArea_Diagnosis.getText().isEmpty())
            i++;
        if(textArea_Testperformed.getText().isEmpty())
            i++;
        if(textArea_Testresults.getText().isEmpty())
            i++;
        if(textArea_Prescribedactions.getText().isEmpty())
            i++;
        if(textArea_Prescribedmeds.getText().isEmpty())
            i++;
        if(textArea_Addnotes.getText().isEmpty())
            i++;
        if(i>0){
            System.out.println("there are empty textfields");
            return true;
        }else{
            System.out.println("no empty textfields");
        }
        return false;
    }

    void clearFields(){
        picker_Date.setValue(null);
        textArea_Description.setText("");
        textField_Veterinarian.setText("");
        textArea_Diagnosis.setText("");
        textArea_Testperformed.setText("");
        textArea_Testresults.setText("");
        textArea_Prescribedactions.setText("");
        textArea_Prescribedmeds.setText("");
        textArea_Addnotes.setText("");
    }
}
