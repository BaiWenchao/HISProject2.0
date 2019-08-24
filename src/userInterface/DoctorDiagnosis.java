package userInterface;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.Show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static userInterface.DoctorLogin.past;
import static userInterface.DoctorLogin.future;


public class DoctorDiagnosis {

    //创建疾病诊断静态列表
    public static ObservableList<Disease> diseases = FXCollections.observableArrayList();

    //创建Show单例
    Show show = Show.getInstance();

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    @FXML
    private TableColumn<Disease, String> diseaseName;

    @FXML
    private Button add;

    @FXML
    private Button submit;

    @FXML
    public TextArea examine;

    @FXML
    private TableColumn<Disease, String> icdCode;

    @FXML
    private TableView<Disease> diagnosis;

    @FXML
    public TextArea describe;

    @FXML
    public TextArea history;

    @FXML
    public TextArea advice;

    @FXML
    public Label recordNumLabel;

    @FXML
    public Label doctor;

    @FXML
    private Button delete;

    @FXML
    public Label nameLabel;

    @FXML
    private void showDisease() throws IOException {
        Parent root = FXMLLoader.load(Entrance.class.getResource("ShowDisease.fxml"));
        show.turnToStage(root,800,300);
    }

    @FXML
    private void initialize(){
        diagnosis.setItems(diseases);

        icdCode.setCellValueFactory(cellData -> cellData.getValue().icdCodeProperty());
        diseaseName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    @FXML
    private void deleteDisease(){
        diseases.remove(diagnosis.getSelectionModel().getFocusedIndex());
    }

    @FXML
    private void submit(){
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(recordNumLabel.getText())){
                List<Disease> diseaseList = new ArrayList<>();
                diseaseList.addAll(diseases);
                Diagnosis diagnosis = new Diagnosis(describe.getText(), history.getText(), examine.getText(), advice.getText(), diseaseList);
                p.getPatientDataList().get(p.getPatientDataList().size()-1).setDiagnosis(diagnosis);

                //对患者列表信息进行增减
                for(Doctor d : hospital.getDoctorList()){
                    if(d.getName().equals(doctor.getText())){
                        for(int i=0; i<d.getFutureList().size(); i++){
                            if(p.getName().equals(d.getFutureList().get(i).getName())){
                                d.getFutureList().remove(i);
                                future.remove(i);
                            }
                        }
                        d.getPastList().add(p);
                        past.add(p);
                        break;
                    }
                }

                break;
            }
        }
    }
}