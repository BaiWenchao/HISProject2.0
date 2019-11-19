package userInterface;

import entity.DiseaseTreeNode;
import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.DataStructure.Algorithms;
import logic.Util;

public class AddDisease {
    // 创建Util单例
    Util util = Util.getInstance();

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
    private TableColumn<DiseaseTreeNode, String> diseaseNameCol;

    @FXML
    private Button addDisease;

    @FXML
    private Button searchPatient;

    @FXML
    private TextField diseaseType;

    @FXML
    private TextField diseaseCode;


    //创建医院单例
    Hospital hospital = Hospital.getInstance();
    //创建数据结构算法类单例
    Algorithms algorithms = Algorithms.getInstance();

    @FXML
    public void setAddDisease() throws Exception {
        try{
            if(!hospital.getDiseaseTree().addNode(diseaseName.getText(), fatherName.getText(), diseaseCode.getText())){
                util.errorInformationAlert("无此父节点！");
            }
        }catch (NullPointerException npe){

        }
        diseaseTreeView.setRoot(algorithms.diseaseBFT(hospital.getDiseaseTree().getNodeMap().get("疾病")));
    }

    @FXML
    public void initialize() throws Exception {
        diseaseTreeView.setRoot(algorithms.diseaseBFT(hospital.getDiseaseTree().getNodeMap().get("疾病")));
    }



}