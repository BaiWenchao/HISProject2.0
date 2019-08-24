package userInterface;

import com.alibaba.fastjson.JSON;
import dataAccess.ReadFile;
import dataAccess.WriteFile;
import entity.Hospital;
import entity.Patient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //创建ReadFile单例
    ReadFile read = ReadFile.getInstance();
    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Entrance.fxml"));
        primaryStage.setTitle("东软云医院HIS系统");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {

        //创建读写类单例
        ReadFile read = ReadFile.getInstance();
        WriteFile write = WriteFile.getInstance();

        //创建医院单例
        Hospital hospital = Hospital.getInstance();


        //读取科室信息
        read.readDepartmentFile();
        //读取医生信息
        read.readDoctorFile();
        //读取疾病信息
        read.readDissaseFile();
        //读取药品信息
        read.readDrugFile();
        //读取处方模板
        try{
            hospital.getPrescriptionTemplateList().clear();
            hospital.getPrescriptionTemplateList().addAll(read.readPrescriptionTempLate());
        }catch (NullPointerException e){

        }
        //读取病人列表
        try{
            hospital.getPatientList().clear();
            hospital.getPatientList().addAll(read.readPatientList());
        }catch (NullPointerException e){

        }
        //读取医生列表
        try{
            if(read.readDoctorList() != null){
                hospital.getDoctorList().clear();
                hospital.getDoctorList().addAll(read.readDoctorList());
            }
        }catch (NullPointerException e){

        }

        launch(args);


        //将病人信息写入文件
        write.writePatientList();
        //将医生信息写入文件
        write.writeDoctorList();

    }
}