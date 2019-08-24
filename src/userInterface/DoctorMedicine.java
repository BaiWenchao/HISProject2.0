package userInterface;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import logic.Show;
import logic.Util;


import java.io.IOException;

public class DoctorMedicine {
    //创建Show单例
    Show show = Show.getInstance();
    //创建util单例
    Util util = Util.getInstance();
    //创建医院单例
    Hospital hospital = Hospital.getInstance();


    //创建处方列表
    public static ObservableList<Prescription> prescriptionObservableList = FXCollections.observableArrayList();
    //创建药品编辑区药品列表
    public static ObservableList<Medicine> medicines = FXCollections.observableArrayList();
    //创建处方模板列表
    public ObservableList<Prescription> prescriptionTemplateList = FXCollections.observableArrayList(hospital.getPrescriptionTemplateList());
    //创建处方模板详情列表
    public static ObservableList<Medicine> templateMedicineList = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Medicine, String> medEditPrice;

    @FXML
    private TableColumn<Medicine, String> medTempSpec;

    @FXML
    private Button submit;

    @FXML
    private Button addMedicine;

    @FXML
    private Button finish;

    @FXML
    private TableColumn<Medicine, String> medEditName;

    @FXML
    private TableColumn<Medicine, String> medEditTime;

    @FXML
    private TableView<Prescription> templateTable;

    @FXML
    private TableView<Medicine> prescriptionEditTable;

    @FXML
    private TableView<Medicine> templateDetailTable;

    @FXML
    private TableColumn<Medicine, String> medEditNum;

    @FXML
    private TableColumn<Medicine, String> medEditUse;

    @FXML
    private Button deletePrescription;

    @FXML
    private TableColumn<Medicine, String> medEditAmount;

    @FXML
    private Label prescriptionNameLabel;

    @FXML
    public Label patientName;

    @FXML
    private TableColumn<Prescription, String> prescriptionNameColumn;

    @FXML
    private TableColumn<Medicine, String> medTempAmount;

    @FXML
    private TableColumn<Medicine, String> medTempTime;

    @FXML
    private Button addPrescription;

    @FXML
    private TableColumn<Medicine, String> medTempUse;

    @FXML
    private Label templatePrescriptionName;

    @FXML
    public Label patientHosRecordNum;

    @FXML
    public Label doctorName;

    @FXML
    private TableColumn<Prescription, String> templateName;

    @FXML
    private TableColumn<Medicine, String> medTempPrice;

    @FXML
    private TableColumn<Medicine, String> medTempNum;

    @FXML
    private TableView<Prescription> prescriptionTable;

    @FXML
    private Button deleteMedicine;

    @FXML
    private Button addIntoListButton;

    @FXML
    private TableColumn<Medicine, String> medTempName;

    @FXML
    private TableColumn<Medicine, String> medEditSpec;

    private AnchorPane addPres;

    @FXML
    private void initialize(){
        //处方列表
        prescriptionTable.setItems(prescriptionObservableList);
        prescriptionNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        prescriptionTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    prescriptionNameLabel.setText(newValue.getName());
                    medicines.clear();
                    medicines.addAll(newValue.getMedicineList());
                })
        );

        //处方编辑区
        prescriptionEditTable.setItems(medicines);
        medEditName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        medEditSpec.setCellValueFactory(cellData -> cellData.getValue().med_specProperty());
        medEditUse.setCellValueFactory(cellData -> cellData.getValue().med_useProperty());
        medEditAmount.setCellValueFactory(cellData -> cellData.getValue().med_amountProperty());
        medEditTime.setCellValueFactory(cellData -> cellData.getValue().med_timeProperty());
        medEditPrice.setCellValueFactory(cellData -> cellData.getValue().med_priceProperty());
        medEditNum.setCellValueFactory(cellData -> cellData.getValue().med_numProperty());

        //处方模板
        templateTable.setItems(prescriptionTemplateList);
        templateName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        templateTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    templatePrescriptionName.setText(newValue.getName());
                    templateMedicineList.clear();
                    templateMedicineList.addAll(newValue.getMedicineList());
                }
        );

        //模板详情
        templateDetailTable.setItems(templateMedicineList);
        medTempName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        medTempSpec.setCellValueFactory(cellData -> cellData.getValue().med_specProperty());
        medTempUse.setCellValueFactory(cellData -> cellData.getValue().med_useProperty());
        medTempAmount.setCellValueFactory(cellData -> cellData.getValue().med_amountProperty());
        medTempTime.setCellValueFactory(cellData -> cellData.getValue().med_timeProperty());
        medTempPrice.setCellValueFactory(cellData -> cellData.getValue().med_priceProperty());
        medTempNum.setCellValueFactory(cellData -> cellData.getValue().med_numProperty());


    }

    @FXML
    private void addPrescription() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddPrescription.fxml"));
        addPres = loader.load();
        AddPrescription addController = loader.getController();
        show.turnToStage(addPres,600,400);

        addController.confirm.setOnAction((ActionEvent e) -> {
            String str = addController.prescriptionName.getText();
            Prescription prescription = new Prescription(str);

            prescriptionObservableList.add(prescription);

            util.completeInformationAlert("处方新建成功！");
        });
    }

    @FXML
    private void deletePrescription(){
        prescriptionObservableList.remove(prescriptionTable.getSelectionModel().getFocusedIndex());
    }

    @FXML
    private void addMedicine() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowMedicine.fxml"));
        show.turnToStage(root,1180,800);
    }

    @FXML
    private void deleteMedicine(){
        medicines.remove(prescriptionEditTable.getSelectionModel().getFocusedIndex());
    }

    @FXML
    private void finishEdit(){
        prescriptionObservableList.get(prescriptionTable.getSelectionModel().getFocusedIndex()).getMedicineList().addAll(medicines);
        medicines.clear();
    }

    @FXML
    private void addToPrescriptionList(){
        Prescription prescription = new Prescription(templatePrescriptionName.getText());
        prescription.getMedicineList().addAll(templateMedicineList);
        prescriptionObservableList.add(prescription);

        templatePrescriptionName.setText("");
        templateMedicineList.clear();
    }

    @FXML
    private void submit(){
        //更新患者列表处方信息
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(patientHosRecordNum.getText())){
                p.getPatientDataList().get(p.getPatientDataList().size()-1).getPrescriptionList().addAll(prescriptionObservableList);
                prescriptionObservableList.clear();
                util.completeInformationAlert("开立成功！");
            }
        }

    }

}
