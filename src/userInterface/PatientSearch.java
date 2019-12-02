package userInterface;

import entity.Department;
import entity.Hospital;
import entity.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logic.Util;

public class PatientSearch {
    // 创建Util单例
    Util util = Util.getInstance();

    // 创建医院单例
    Hospital hospital = Hospital.getInstance();

    ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Patient, String> numColumn;

    @FXML
    private TableColumn<Patient, String> timeColumn;

    @FXML
    private Button search;

    @FXML
    private ChoiceBox<String> departmentBox;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableView<Patient> infoTable;

    @FXML
    private Button idSort;

    @FXML
    private Button nameSort;

    @FXML
    private void initialize(){
        infoTable.setItems(patientObservableList);

        timeColumn.setCellValueFactory(cellData -> cellData.getValue().currentInsertTimeProperty());
        numColumn.setCellValueFactory(cellData -> cellData.getValue().hosRecordNumProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    @FXML
    private void initDepartment(){
        ObservableList<String> choices = FXCollections.observableArrayList("内科","外科","医院");
        departmentBox.setItems(choices);
    }

    @FXML
    private void setSearch(){
        String range = departmentBox.getValue();
        if(range == null){
            util.errorInformationAlert("请选择查询范围！");
            return;
        }

        if(range.equals("内科")){
            for(Department d : hospital.getDepartmentList()){
                if(d.getName().equals("内科")){
                    patientObservableList.clear();
                    patientObservableList.addAll(d.getPatientList());
                }
            }
        }else if(range.equals("外科")){
            for(Department d : hospital.getDepartmentList()){
                if(d.getName().equals("外科")){
                    patientObservableList.clear();
                    patientObservableList.addAll(d.getPatientList());
                }
            }
        }else if(range.equals("医院")){
            patientObservableList.clear();
            for(Department d : hospital.getDepartmentList()){
                patientObservableList.addAll(d.getPatientList());
            }
        }
    }
}
