package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;

public class Medicalhistory implements Initializable{
	@FXML
	private Text petNameText;
	@FXML
	private Text breederNameText;
	@FXML
	private Text formerVetClinicText;
	@FXML
	private Text formerVetText;
	@FXML
	private Text neuterDateText;
	@FXML
	private Text neuterText;

    @FXML
    private TextField textfield_formerVetclinic;

    @FXML
    private Button btn_back;

    @FXML
    private TextField textfield_formerVeterinarian;

    @FXML
    private TextField textFiled_breedersName;

    @FXML
    private RadioButton radiobutton_snYes;

    @FXML
    private RadioButton radiobutton_snNo;

    @FXML
    private TextField textArea_pastSurgical;

    @FXML
    private TextField textfield_surgicalProcedure;

    @FXML
    private TextField textfield_diagnosis;

    @FXML
    private TextField textfield_treatment;

    @FXML
    private TextField textArea_illness;

    @FXML
    private TableColumn<?, ?> psDAte;

    @FXML
    private TableColumn<?, ?> psSurgicalprocedure;

    @FXML
    private TableColumn<?, ?> psAddinfo;

    @FXML
    private TableColumn<?, ?> iaDiagnosis;

    @FXML
    private TableColumn<?, ?> iaTreatment;

    @FXML
    private TableColumn<?, ?> iaAddinfo;

    @FXML
    private DatePicker picker_spayOrneuter;
    
    @FXML
    private ToggleGroup yesOrNo;
    
    PetHistory ph;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	petNameText.setText(Pethealth.selectedPet.getAnimalName());
		try {
			ph = PetHistory.getPetHistory(Pethealth.selectedPet);
		}catch(Exception e) {
			ph = null;
		}
		
		if(ph != null) {
			System.out.println("There is History!");
			setHistory(ph);
		}else {
			System.out.println("No History Yet!");
			invisibleText();
		}
		
	}
    
    public void save() throws Exception{
    	System.out.println("Saving!");
    	if(ph == null) {
    		if (textFieldsEmpty()) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Pet");
        		alert.setHeaderText("Please fill up all fields!");
        		alert.showAndWait();
    		} else {
    			ph = new PetHistory();
    			ph.setBreederName(textFiled_breedersName.getText());
    			ph.setFormerVetClinic(textfield_formerVetclinic.getText());
    			ph.setFormerVet(textfield_formerVeterinarian.getText());
    			ph.setDate(picker_spayOrneuter.getValue());
    			RadioButton selected = (RadioButton) yesOrNo.getSelectedToggle();
    			ph.setNeutered(selected.getText());
    			Database.saveHistory(ph, Pethealth.selectedPet);
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Immunization");
    			alert.setHeaderText("Pet Immunization Record is added succesfully!");
    			alert.showAndWait();
    			setHistory(ph);
    			clearFields();
    		}
    	}
			
    }
    
    boolean textFieldsEmpty() {
		int i = 0;
		if (textFiled_breedersName.getText().isEmpty()) {
			i++;
		}
		if (textfield_formerVetclinic.getText().isEmpty()) {
			i++;
		}
		if (textfield_formerVeterinarian.getText().isEmpty()) {
			i++;
		}
		if (picker_spayOrneuter.getValue() == null) {
			i++;
		}
		if (yesOrNo.getSelectedToggle() == null) {
			i++;
		}
		
		if(picker_spayOrneuter.getValue() == null && radiobutton_snYes.isSelected()) {
			i++;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Spay or Neuter");
			alert.setHeaderText("Please set date!");
			alert.showAndWait();
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
    	textFiled_breedersName.setText("");
    	textfield_formerVetclinic.setText("");
    	textfield_formerVeterinarian.setText("");
    	picker_spayOrneuter.setValue(null);
    	yesOrNo.selectToggle(null);
    }
    
    void setHistory(PetHistory h) {
    	//invisible Input Fields
    	textFiled_breedersName.setVisible(false);
    	textfield_formerVetclinic.setVisible(false);
    	textfield_formerVeterinarian.setVisible(false);
    	picker_spayOrneuter.setVisible(false);
    	radiobutton_snYes.setVisible(false);
    	radiobutton_snNo.setVisible(false);
    	
    	
    	breederNameText.setText(h.getBreederName());
		formerVetClinicText.setText(h.getFormerVetClinic());
		formerVetText.setText(h.getFormerVet());
		neuterDateText.setText(h.getDate().toString());
		neuterText.setText(h.getNeutered());
		
    	breederNameText.setVisible(true);
		formerVetClinicText.setVisible(true);
		formerVetText.setVisible(true);
		neuterDateText.setVisible(true);
		neuterText.setVisible(true);
    	
    }
    
    void invisibleText() {
    	//Invisible Texts
    	breederNameText.setVisible(false);
		formerVetClinicText.setVisible(false);
		formerVetText.setVisible(false);
		neuterDateText.setVisible(false);
    }
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }


	
}

