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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<PetProcedure, String> date;

    @FXML
    private TableColumn<PetProcedure, String> procedure;

    @FXML
    private TableColumn<PetProcedure, String> additional;

    @FXML
    private TableColumn<PetIllness, String> diagnosis;

    @FXML
    private TableColumn<PetIllness, String> treatment;

    @FXML
    private TableColumn<PetIllness, String> information;
    
    @FXML
    private TableView<PetIllness> illnessTable;
    @FXML 
    private TableView<PetProcedure> surgicalTable;

    @FXML
    private DatePicker picker_spayOrneuter;
    @FXML
    private DatePicker picker_surgicalDate;
    
    @FXML
    private ToggleGroup yesOrNo;
    
    PetHistory ph;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	petNameText.setText(Pethealth.selectedPet.getAnimalName());
		try {
			System.out.println("Getting history");
			ph = PetHistory.getPetHistory(Pethealth.selectedPet);
		}catch(Exception e) {
			System.out.println(e);
			ph = null;
		}
		
		if(ph != null) {
			System.out.println("There is History!");
			setHistory(ph);
		}else {
			System.out.println("No History Yet!");
			invisibleText();
		}
		//IllnessTabale
		illnessTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		diagnosis.setCellValueFactory(new PropertyValueFactory<PetIllness, String>("diagnosis"));
		treatment.setCellValueFactory(new PropertyValueFactory<PetIllness, String>("treatment"));
		information.setCellValueFactory(new PropertyValueFactory<PetIllness, String>("information"));
		
		//SurgicalTable
		surgicalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		date.setCellValueFactory(new PropertyValueFactory<PetProcedure, String>("date"));
		procedure.setCellValueFactory(new PropertyValueFactory<PetProcedure, String>("procedure"));
		additional.setCellValueFactory(new PropertyValueFactory<PetProcedure, String>("additional"));
		
		try {
			illnessTable.setItems(PetIllness.getPetIllnessList(Pethealth.selectedPet));
			surgicalTable.setItems(PetProcedure.getPetProcedureList(Pethealth.selectedPet));
		} catch (Exception e) {
			System.out.println(e);
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
    			alert.setTitle("Medical History");
    			alert.setHeaderText("Pet Medical History Record is added succesfully!");
    			alert.showAndWait();
    			setHistory(ph);
    			clearFields();
    		}
    	}else {
    		if (textFieldsEmpty()) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Pet");
    			alert.setHeaderText("Please fill up all fields!");
    			alert.showAndWait();
    		}else {
    			int flag = 0;
    			if(!textfield_diagnosis.getText().isEmpty() || !textfield_treatment.getText().isEmpty() || !textArea_illness.getText().isEmpty()) {
    				PetIllness petIllness = new PetIllness();
        			petIllness.setDiagnosis(textfield_diagnosis.getText());
        			petIllness.setTreatment(textfield_treatment.getText());
        			petIllness.setInformation(textArea_illness.getText());
        			Database.saveIllness(petIllness, Pethealth.selectedPet);
        			Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Immunization");
        			alert.setHeaderText("Pet Illness Record is added succesfully!");
        			alert.showAndWait();
        			illnessTable.setItems(PetIllness.getPetIllnessList(Pethealth.selectedPet));
        			flag++;
    			}else System.out.println("No new illness to be saved");
    			
    			if(picker_surgicalDate.getValue() != null || !textfield_surgicalProcedure.getText().isEmpty() || !textArea_pastSurgical.getText().isEmpty()) {
    				PetProcedure petProcedure = new PetProcedure();
    				petProcedure.setDate(picker_surgicalDate.getValue());
    				petProcedure.setProcedure(textfield_surgicalProcedure.getText());
    				petProcedure.setAdditional(textArea_pastSurgical.getText());
    				Database.saveProcedure(petProcedure, Pethealth.selectedPet);
    				Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Immunization");
        			alert.setHeaderText("Pet Surgical Record is added succesfully!");
        			alert.showAndWait();
    				surgicalTable.setItems(PetProcedure.getPetProcedureList(Pethealth.selectedPet));
    				flag++;
    			}else System.out.println("No new surgical procedure to be saved");
    			
    			System.out.println(flag);
    			if(flag == 0) {
    				Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Pet");
        			alert.setHeaderText("No new information to be saved!");
        			alert.showAndWait();
    			}
    			clearFields();
    		}
    	}
			
    }
    
    boolean textFieldsEmpty() {
		int i = 0;
		if(ph == null) { //Check only if ph is null
			if (textFiled_breedersName.getText().isEmpty()) {
				i++;
			}
			if (textfield_formerVetclinic.getText().isEmpty()) {
				i++;
			}
			if (textfield_formerVeterinarian.getText().isEmpty()) {
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
		}else {
			if(!textfield_diagnosis.getText().isEmpty() || !textfield_treatment.getText().isEmpty() || !textArea_illness.getText().isEmpty()) {
				if(textfield_diagnosis.getText().isEmpty()) {
					i++;
				}
				if(textfield_treatment.getText().isEmpty()) {
					i++;
				}
			}
			if(picker_surgicalDate.getValue() != null || !textfield_surgicalProcedure.getText().isEmpty() || !textArea_pastSurgical.getText().isEmpty()) {
				if(picker_surgicalDate.getValue() == null) {
					i++;
				}
				if(textfield_surgicalProcedure.getText().isEmpty()) {
					i++;
				}
				
			}
			
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
    	
    	//Illness
    	textfield_diagnosis.setText("");
    	textfield_treatment.setText("");
    	textArea_illness.setText("");
    	
    	picker_surgicalDate.setValue(null);
    	textfield_surgicalProcedure.setText("");
    	textArea_pastSurgical.setText("");
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
		
		neuterText.setText(h.getNeutered());
		if(neuterText.getText() == "Yes") {
			neuterDateText.setText(h.getDate().toString());
		}else {
			neuterDateText.setText("");
		}
		
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
		neuterText.setVisible(false);
    }
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }


	
}

