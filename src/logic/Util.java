package logic;

import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;

public class Util {

    //单例模式：
    private static Util instance;
    private Util(){}
    public static synchronized Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }


    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建Show单例
    Show show = Show.getInstance();

    //患者登录
    public String canPatientLogin(String num){
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(num)){
                return p.getName();
            }
        }
        return null;
    }

    //错误提示信息界面
    public void errorInformationAlert(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("错误");
        alert.headerTextProperty().set(str);
        alert.showAndWait();
    }

    //完成提示信息
    public void completeInformationAlert(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("提示信息");
        alert.headerTextProperty().set(str);
        alert.showAndWait();
    }

    //医生登录
    public void docLogin(String name, String code) throws IOException {

        if(name.equals("ht") && code.equals("ht")){
            show.showDoctorRoot("华佗",0);
            return;
        }

        if(name.equals("bq") && code.equals("bq")){
            show.showDoctorRoot("扁鹊",1);
            return;
        }

        if(name.equals("ssm") && code.equals("ssm")){
            show.showDoctorRoot("孙思邈",2);
            return;
        }

        if(name.equals("lsz") && code.equals("lsz")){
            show.showDoctorRoot("李时珍",3);
            return;
        }

        if(name.equals("xb") && code.equals("xb")){
            show.showDoctorRoot("希波克拉底",4);
            return;
        }

        if(name.equals("gl") && code.equals("gl")){
            show.showDoctorRoot("盖伦",5);
            return;
        }

        if(name.equals("ph") && code.equals("ph")){
            Parent root = FXMLLoader.load(getClass().getResource("/userInterface/PharmacyDistribute.fxml"));
            show.turnToStage(root,1500,900);
            return;
        }

        if(name.equals("ma") && code.equals("ma")){
            Parent root = FXMLLoader.load(getClass().getResource("/userInterface/Arrange.fxml"));
            show.turnToStage(root,1500,900);
            return;
        }

        errorInformationAlert("用户名或密码错误！");

    }

}
