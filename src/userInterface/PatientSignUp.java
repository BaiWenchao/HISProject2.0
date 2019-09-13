package userInterface;

import com.alibaba.fastjson.JSON;
import dataAccess.WriteFile;
import entity.Hospital;
import entity.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.PatientSignUpClass;
import logic.ReturnNum;
import logic.Util;

public class PatientSignUp {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建返回病案号方法的单例
    ReturnNum returnNum = ReturnNum.getInstance();

    //创建Util单例
    Util util = Util.getInstance();

    //创建患者注册方法的单例
    PatientSignUpClass patientSignUpClass = PatientSignUpClass.getInstance();

    @FXML
    private DatePicker birthday;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField hosRecordNum;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNum;

    @FXML
    private Button regist;

    @FXML
    private TextField homeAddress;

    @FXML
    private TextField IDNum;

    @FXML
    private void regist(){
        //数据缺失处理
        if(hosRecordNum.getText() == null || name.getText() == null || gender.getValue() == null || birthday.getValue() == null){
            util.errorInformationAlert("相关信息未完善！");
            return;
        }

        //将患者信息添加至医院的患者列表
        patientSignUpClass.patientSignUp(hosRecordNum.getText(), name.getText(), gender.getValue(), birthday.getValue().toString(), homeAddress.getText(), phoneNum.getText(), IDNum.getText());

        //注册成功提示
        util.completeInformationAlert("注册成功！");
    }

    @FXML
    private void initGender(){
        ObservableList<String> choices = FXCollections.observableArrayList("男","女");
        gender.setItems(choices);
    }

    @FXML
    private void initialize(){
        hosRecordNum.setText(returnNum.returnHosRecordNum());
    }

}
