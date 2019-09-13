package userInterface;

import entity.Hospital;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.Calculate;
import logic.Show;
import logic.Util;

import java.io.IOException;

public class PatientLogin {

    //创建Show单例
    Show show = Show.getInstance();
    //创建Util单例
    Util util = Util.getInstance();
    //创建医院单例
    Hospital hospital = Hospital.getInstance();
    //创建Calculate单例
    Calculate calculate = Calculate.getInstance();




    //创建两个静态变量存储登录患者姓名及病案号
    public static String patientName;
    public static String patientHosRecordNum;

    @FXML
    private TextField hosRecordNum;

    @FXML
    private Button SignIn;

    @FXML
    private Button signUp;


    //患者注册
    @FXML
    private void turnToRatientSignUp() throws IOException {
        Parent root = FXMLLoader.load(Entrance.class.getResource("PatientSignUp.fxml"));
        show.turnToStage(root,1500,900);
    }

    //患者登录
    @FXML
    private void turnToPatient() throws IOException {
        if((hospital.getPatientList().size() > 0) && (util.canPatientLogin(hosRecordNum.getText()) != null)){
            //为两个静态变量赋值
            patientName = util.canPatientLogin(hosRecordNum.getText());
            patientHosRecordNum = hosRecordNum.getText();

            Parent root = FXMLLoader.load(Entrance.class.getResource("PatientRoot.fxml"));
            //为各个医生刷新其工作状态
            try{
                calculate.isWork();
                show.turnToStage(root,1500,900);
            }catch (NullPointerException e){
                util.errorInformationAlert("尚未给医生排班！");
            }
        }else {
            util.errorInformationAlert("无此病案号！");
            return;
        }
    }

}
