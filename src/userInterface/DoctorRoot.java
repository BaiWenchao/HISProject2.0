package userInterface;

import entity.Patient;
import entity.Records;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import logic.DataStructure.Algorithms;
import logic.Show;
import logic.Util;

import java.io.IOException;

import static userInterface.DoctorDiagnosis.diseases;
import static userInterface.DoctorLogin.past;
import static userInterface.DoctorLogin.future;


public class DoctorRoot {

    // 创建算法类单例
    Algorithms algorithms = Algorithms.getInstance();

    // 创建Show单例
    Show show = Show.getInstance();

    // 创建Util单例
    Util util = Util.getInstance();

    @FXML
    public Label doctorName;

    @FXML
    private TableColumn<Patient, String> futureName;

    @FXML
    private TableColumn<Patient, String> pastName;

    @FXML
    private TableColumn<Patient, String> futureNum;

    @FXML
    private TableColumn<Patient, String> pastNum;

    @FXML
    private TableView<Patient> pastTable;

    @FXML
    private TableView<Patient> futureTable;

    @FXML
    private Tab diagnosis;

    @FXML
    private Tab medicine;

    @FXML
    private Tab rediagnosis;

    @FXML
    public Label department;

    @FXML
    private Button nextButton;

    @FXML
    private RadioButton showRecords;

    // 3个tab下属AnchorPane
    private AnchorPane rootDiagnosis;
    private AnchorPane rootMedicine;
    private AnchorPane rootReDiagnosis;

    private AnchorPane showrecords;

    // 3个AnchorPane的控制器
    private DoctorDiagnosis docDiag;
    private DoctorMedicine docMed;
    private DoctorDiagnosisAgain docDiagAga;

    @FXML
    private void initialize() throws IOException {
        //加载诊断页面
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("DoctorDiagnosis.fxml"));
        rootDiagnosis = loader1.load();
          //获取DoctorDiagnosis控制器
        docDiag = loader1.getController();
        diagnosis.setContent(rootDiagnosis);
        //加载开方页面
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("DoctorMedicine.fxml"));
        rootMedicine = loader2.load();
          //获取DoctorMedicine控制器
        docMed = loader2.getController();
        medicine.setContent(rootMedicine);

        // 加载复诊界面
        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(getClass().getResource("DoctorDiagnosisAgain.fxml"));
        rootReDiagnosis = loader3.load();

        // 获取DoctorDiagnosisAgain控制器
        docDiagAga = loader3.getController();
        rediagnosis.setContent(rootReDiagnosis);

        futureTable.setItems(future);
        pastTable.setItems(past);

        futureNum.setCellValueFactory(cellData -> cellData.getValue().currentRecordNumProperty());
        futureName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pastNum.setCellValueFactory(cellData -> cellData.getValue().hosRecordNumProperty());
        pastName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());


        futureTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try{
                        docDiag.recordNumLabel.setText(newValue.getHosRecordNum());
                        docDiag.nameLabel.setText(newValue.getName());
                        docDiag.doctor.setText(doctorName.getText());
                        docDiag.department.setText(department.getText());
                        docDiag.describe.setText("");
                        docDiag.history.setText("");
                        docDiag.examine.setText("");
                        docDiag.advice.setText("");

                        docDiagAga.hosRecordNumLabel.setText(newValue.getHosRecordNum());
                        docDiagAga.nameLabel.setText(newValue.getName());
                        docDiagAga.doctorLabel.setText(doctorName.getText());
                        docDiagAga.departmentLabel.setText(department.getText());

                        diseases.clear();
                    }catch (NullPointerException ne){

                    }
                });

        pastTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    try{
                        docDiag.recordNumLabel.setText(newValue.getHosRecordNum());
                        docDiag.nameLabel.setText(newValue.getName());
                        docDiag.doctor.setText(doctorName.getText());

                        docDiagAga.hosRecordNumLabel.setText(newValue.getHosRecordNum());
                        docDiagAga.nameLabel.setText(newValue.getName());
                        docDiagAga.departmentLabel.setText(department.getText());
                        docDiagAga.doctorLabel.setText(doctorName.getText());

                        int num = newValue.getPatientDataList().size()-1;
                        docDiag.describe.setText(newValue.getPatientDataList().get(num).getDiagnosis().getDescribe());
                        docDiag.history.setText(newValue.getPatientDataList().get(num).getDiagnosis().getHistory());
                        docDiag.examine.setText(newValue.getPatientDataList().get(num).getDiagnosis().getExamine());
                        docDiag.advice.setText(newValue.getPatientDataList().get(num).getDiagnosis().getAdvice());
                        diseases.clear();
                        diseases.addAll(newValue.getPatientDataList().get(num).getDiagnosis().getDiseaseList());

                        docMed.patientName.setText(newValue.getName());
                        docMed.patientHosRecordNum.setText(newValue.getHosRecordNum());
                        docMed.doctorName.setText(doctorName.getText());
                    }catch (NullPointerException e){

                    }
                })
        );
    }

    @FXML
    private void showNext() throws IOException {
        String hosRecordNum = future.get(0).getHosRecordNum();
        String name = future.get(0).getName();
        docDiag.recordNumLabel.setText(hosRecordNum);
        docDiag.nameLabel.setText(name);
        docDiag.doctor.setText(doctorName.getText());
        docDiag.department.setText(department.getText());
        docDiag.describe.setText("");
        docDiag.history.setText("");
        docDiag.examine.setText("");
        docDiag.advice.setText("");

        docDiagAga.hosRecordNumLabel.setText(hosRecordNum);
        docDiagAga.nameLabel.setText(name);
        docDiagAga.doctorLabel.setText(doctorName.getText());
        docDiagAga.departmentLabel.setText(department.getText());

        diseases.clear();


        // 展示该病人的就诊记录
        ObservableList<Records> records = FXCollections.observableArrayList();
            // 用遍历法搜索患者记录
        algorithms.searchRecords_Common(records, hosRecordNum);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Entrance.class.getResource("ShowRecords.fxml"));
        showrecords = loader.load();

        ShowRecords controller = loader.getController();
        controller.recordTable.setItems(records);

        controller.numColumn.setCellValueFactory(cellData -> cellData.getValue().patientIDProperty());
        controller.timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        controller.docColumn.setCellValueFactory(cellData -> cellData.getValue().docNameProperty());

        if(!showRecords.isSelected()){
            show.turnToStage(showrecords,500,600);
        }

    }

    @FXML
    private void fresh(){
        futureTable.setItems(future);
        pastTable.setItems(past);

        futureNum.setCellValueFactory(cellData -> cellData.getValue().currentRecordNumProperty());
        futureName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pastNum.setCellValueFactory(cellData -> cellData.getValue().hosRecordNumProperty());
        pastName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        util.completeInformationAlert("刷新成功！");
    }


}
