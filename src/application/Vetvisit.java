package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Vetvisit {

    @FXML
    private Button btn_back;

    @FXML
    private DatePicker picker_Date;

    @FXML
    private TextArea textArea_Description;

    @FXML
    private TextField textField_Veterinarian;

    @FXML
    private TextArea textArea_Diagnosis;

    @FXML
    private TextArea textArea_Testperformed;

    @FXML
    private TextArea textArea_Testresults;

    @FXML
    private TextArea textArea_Prescribedactions;

    @FXML
    private TextArea textArea_Prescribedmeds;

    @FXML
    private TextArea textArea_Addnotes;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> veterinarian;

    @FXML
    private TableColumn<?, ?> diagnosis;

    @FXML
    private TableColumn<?, ?> testperformed;

    @FXML
    private TableColumn<?, ?> testresults;

    @FXML
    private TableColumn<?, ?> prescribedactions;

    @FXML
    private TableColumn<?, ?> prescribedmeds;

    @FXML
    private TableColumn<?, ?> additionalnotes;


    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }
}
