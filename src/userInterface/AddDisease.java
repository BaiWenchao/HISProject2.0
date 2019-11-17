package userInterface;

import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.DataStructure.Algorithms;

public class AddDisease {
    @FXML
    private TreeView<String> diseaseTreeView;

    @FXML
    private TextField diseaseName;

    @FXML
    private TextField fatherName;

    @FXML
    private TableColumn<Patient, String> patientName;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, String> hosRecordNum;

    @FXML
    private Button addDisease;

    @FXML
    private Button searchPatient;

    @FXML
    private TextField diseaseType;


    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    @FXML
    public void setAddDisease(){
        hospital.getDiseaseTree().addNode(diseaseName.getText(), fatherName.getText());
    }

    @FXML
    public void initialize() throws Exception {
        Algorithms algorithms = Algorithms.getInstance();
        diseaseTreeView.setRoot(algorithms.diseaseBFT(hospital.getDiseaseTree().getNodeMap().get("疾病")));
    }



}