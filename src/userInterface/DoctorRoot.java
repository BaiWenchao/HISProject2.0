package userInterface;

import entity.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static userInterface.DoctorDiagnosis.diseases;
import static userInterface.DoctorLogin.past;
import static userInterface.DoctorLogin.future;


public class DoctorRoot {

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


    private AnchorPane rootDiagnosis;
    private AnchorPane rootMedicine;

    @FXML
    private void initialize() throws IOException {
        //加载诊断页面
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("DoctorDiagnosis.fxml"));
        rootDiagnosis = loader1.load();
          //获取DoctorDiagnosis控制器
        DoctorDiagnosis docDiag = loader1.getController();
        diagnosis.setContent(rootDiagnosis);
        //加载开方页面
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("DoctorMedicine.fxml"));
        rootMedicine = loader2.load();
          //获取DoctorMedicine控制器
        DoctorMedicine docMed = loader2.getController();
        medicine.setContent(rootMedicine);


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
                        docDiag.describe.setText("");
                        docDiag.history.setText("");
                        docDiag.examine.setText("");
                        docDiag.advice.setText("");
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


}
