package userInterface;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.DataStructure.Algorithms;
import logic.Util;

import java.util.HashMap;

public class AddDisease {
    @FXML
    private TreeView<String> diseaseTreeView;

    @FXML
    private TextField diseaseName;

    @FXML
    private TextField fatherName;

    @FXML
    private TableColumn<ShowPatientUnderDisease, String> patientName;

    @FXML
    private TableView<ShowPatientUnderDisease> patientTable;

    @FXML
    private TableColumn<ShowPatientUnderDisease, String> hosRecordNum;

    @FXML
    private TableColumn<ShowPatientUnderDisease, String> diseaseNameCol;

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
    // 创建Util单例
    Util util = Util.getInstance();

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

    @FXML
    public void setSearchPatient() throws Exception {
        String name = diseaseType.getText();
        patientTable.setItems(algorithms.diseaseBFTwithDiseaseList(name));

        patientName.setCellValueFactory(cellData -> cellData.getValue().patientNameProperty());
        hosRecordNum.setCellValueFactory(cellData -> cellData.getValue().recordNumProperty());
        diseaseNameCol.setCellValueFactory(cellData -> cellData.getValue().diseaseNameProperty());
    }



}