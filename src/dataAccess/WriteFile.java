package dataAccess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import entity.Hospital;
import entity.Patient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static userInterface.DoctorMedicine.prescriptionObservableList;

public class WriteFile {
    //单例模式：
    private static WriteFile instance;
    private WriteFile(){}
    public static synchronized WriteFile getInstance(){
        if(instance == null){
            instance = new WriteFile();
        }
        return instance;
    }

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //将医院的病人列表写入文件
    public void writePatientList(){
        //将列表转化为字符串
        String str = JSON.toJSONString(hospital.getPatientList());
        //将字符串存入文件
        File f = new File(getClass().getResource("PatientList.data").getPath());
        try(FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw)){
            bfw.write(str);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeDoctorList(){
        //将列表转化为字符串
        String str = JSON.toJSONString(hospital.getDoctorList());
        //将字符串存入文件
        File f = new File(getClass().getResource("DoctorList.data").getPath());
        try(FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw)){
            bfw.write(str);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
