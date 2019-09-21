package userInterface;

import entity.Department;
import entity.Doctor;
import entity.Hospital;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static userInterface.PatientLogin.patientHosRecordNum;
import static userInterface.PatientLogin.patientName;

public class PatientRegist {

    //创建Show单例
    Show show = Show.getInstance();

    //创建ReturnNum单例
    ReturnNum returnNum = ReturnNum.getInstance();

    //创建Util单例
    Util util = Util.getInstance();

    //创建Calculate单例
    Calculate calculate = Calculate.getInstance();

    //创建Save单例
    Save save = Save.getInstance();

    //创建hospital单例
    Hospital hospital = Hospital.getInstance();

    public static String registFee;

    @FXML
    private ChoiceBox<String> doctor;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton needRecordBook;

    @FXML
    private ChoiceBox<String> isExpert;

    @FXML
    private Label hosRecordNum;

    @FXML
    private Label name;

    @FXML
    private ChoiceBox<String> wayToPay;

    @FXML
    private ChoiceBox<String> department;

    @FXML
    private TextField recordNum;

    @FXML
    private Button payOnline;

    private AnchorPane pay;

    //初始化choiceBox
    @FXML
    private void initDepartment(){
        List<String> departmentNameList = new ArrayList<>();
        for(Department d : hospital.getDepartmentList()){
            departmentNameList.add(d.getName());
        }
        ObservableList<String> choices = FXCollections.observableArrayList(departmentNameList);
        department.setItems(choices);
    }

    @FXML
    private void initIsExpert(){
        ObservableList<String> choices = FXCollections.observableArrayList("专家号","普通号");
        isExpert.setItems(choices);
    }

    @FXML
    private void initWayToPay(){
        ObservableList<String> choices = FXCollections.observableArrayList("微信支付","支付宝支付");
        wayToPay.setItems(choices);
    }

    @FXML
    private void initDoctor(){
        String departmentName = department.getValue();
        String expert = isExpert.getValue();
        List<String> doctorName = new ArrayList<>();

        for(Doctor d : hospital.getDoctorList()){
            if(d.getDepartment().equals(departmentName) && ((expert.equals("专家号") && d.getLevel().equals("0")) || (expert.equals("普通号") && d.getLevel().equals("1"))) && d.isWork()){
                doctorName.add(d.getName());
            }
        }
        ObservableList<String> choices = FXCollections.observableArrayList(doctorName);
        doctor.setItems(choices);

        if(choices.size() == 0){
            util.errorInformationAlert("尚无排班医生！");
        }
    }


    //打开支付二维码界面，同时保存患者的挂号信息，同时将该患者加入对应医生的未诊患者列表
    @FXML
    private void turnToPayRegist() throws IOException {
        try{
            registFee = calculate.registCalculate(needRecordBook.isSelected(),isExpert.getValue());
        }catch (NullPointerException e){

        }

        if(recordNum.getText() == null || date.getValue() == null || department.getValue() == null || isExpert.getValue() == null || doctor.getValue() == null || wayToPay.getValue() == null){
            util.errorInformationAlert("请完善挂号信息！");
            return;
        }


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Pay.fxml"));
        pay = loader.load();
        Pay controller = loader.getController();
        controller.howMuch.setText(registFee);
        show.turnToStage(pay,500,400);


        controller.pay.setOnAction((ActionEvent e) -> {
            //将挂号信息存入该患者，并将该患者挂号至对应医生
            save.registInfoSave(patientHosRecordNum, recordNum.getText(), needRecordBook.isSelected(), date.getValue().toString(), department.getValue(), isExpert.getValue(), doctor.getValue(), wayToPay.getValue());
            //关闭二维码界面
            Stage stage = (Stage) controller.pay.getScene().getWindow();
            stage.close();

            util.completeInformationAlert("挂号成功！");
        });

    }

    //初始化患者信息
    @FXML
    private void initialize(){
        hosRecordNum.setText(patientHosRecordNum);
        name.setText(patientName);

        recordNum.setText(returnNum.returnRecordNum());
    }

}
