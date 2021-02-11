package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Medicalhistory {

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

    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }
}

