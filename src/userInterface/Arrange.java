package userInterface;

import entity.Department;
import entity.Doctor;
import entity.Hospital;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.Calculate;
import logic.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Arrange {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建Util单例
    Util util = Util.getInstance();

    //创建Calculate单例
    Calculate calculate = Calculate.getInstance();

    //创建医生列表
    public ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList();

    @FXML
    private Button confirm;

    @FXML
    private Label docName;

    @FXML
    private TableColumn<Doctor, String> depCol;

    @FXML
    private TableColumn<Doctor, String> docCol;

    @FXML
    private ChoiceBox<String> rule;

    @FXML
    private TableColumn<Doctor, String> rulCol;

    @FXML
    private Label department;

    @FXML
    private TableView<Doctor> table;

    @FXML
    private void initRule(){
        ObservableList<String> choices = FXCollections.observableArrayList("1010101","1101010");
        rule.setItems(choices);
    }


    @FXML
    private void initialize(){
        doctorObservableList.addAll(hospital.getDoctorList());
        table.setItems(doctorObservableList);
        depCol.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        docCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        rulCol.setCellValueFactory(cellData -> cellData.getValue().ruleProperty());

        table.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    department.setText(newValue.getDepartment());
                    docName.setText(newValue.getName());
                })
        );
    }

    @FXML
    private void submit(){
        for(Doctor d : hospital.getDoctorList()){
            if(d.getName().equals(docName.getText())){
                //设置排班规则
                d.setRule(rule.getValue());
            }
        }
    }

    @FXML
    private void completeEdit(){
        calculate.isWork();
        util.completeInformationAlert("编辑完成！");
    }
}
