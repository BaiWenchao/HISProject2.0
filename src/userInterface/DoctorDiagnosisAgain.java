package userInterface;

import entity.DiseaseTreeNode;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextArea advice;

    @FXML
    private TableColumn<DiseaseTreeNode, String> icdCode;

    @FXML
    private TableView<DiseaseTreeNode> diagnosis;

    @FXML
    public Label nameLabel;

    @FXML
    public Label hosRecordNumLabel;

    @FXML
    public Label doctorLabel;

    @FXML
    public Label departmentLabel;
}
