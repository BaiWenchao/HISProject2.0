package userInterface;

import entity.DiseaseTreeNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class DoctorDiagnosisAgain {
    @FXML
    private TableColumn<DiseaseTreeNode, String> diseaseName;

    @FXML
    private Button add;

    @FXML
    private Button submit;

    @FXML
    private Button delete;

    @FXML
    private Button turnToReDiagnosis;

    @FXML
    private TextArea advice;

    @FXML
    private TableColumn<DiseaseTreeNode, String> icdCode;

    @FXML
    private TableView<DiseaseTreeNode> diagnosis;
}
