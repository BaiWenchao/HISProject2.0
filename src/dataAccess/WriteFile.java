package dataAccess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import entity.*;
import logic.DataStructure.MyPriorityQueue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    //创建药房单例
    Pharmacy pharmacy = Pharmacy.getInstance();

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

    //将医生信息写入文件
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

    //将药品信息写入文件
    public void writeMedicine(){
        File f = new File(getClass().getResource("Drug.txt").getPath());
        //清空上回内容
        try(FileWriter writer = new FileWriter(f)){
            writer.write("");
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

        String str;

        Set<Map.Entry<Medicine,String>> entries = pharmacy.getMedicineStringMap().entrySet();
        for(Map.Entry<Medicine,String> entry : entries){
            str = entry.getKey().toString() + entry.getValue();
            try(FileWriter fw = new FileWriter(f,true);
                BufferedWriter bfw = new BufferedWriter(fw)){
                bfw.write(str);
                bfw.newLine();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    // 写入存储疾病树的map
    public void writeDiseaseTree(){
        /*//将map转化为字符串
        String str = JSON.toJSONString(hospital.getDiseaseTree().getNodeMap());*/

        // 策略2：将map中的node转移到list中，存储list
        List<DiseaseTreeNode> nodeList = new ArrayList<>();
        for(String s : hospital.getDiseaseTree().getNodeMap().getKeyList()){
            nodeList.add(hospital.getDiseaseTree().getNodeMap().get(s));
        }
        String str = JSON.toJSONString(nodeList);

        //将字符串存入文件
        File f = new File(getClass().getResource("DiseaseTree.data").getPath());
        try(FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw)){
            bfw.write(str);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 写入当天挂号数
    public void writeRecordNum(){
        File f = new File(getClass().getResource("recordNum.txt").getPath());
        String str = hospital.getRecordNum().toString() + "," + hospital.getNumMap().get("华佗") + ","
                    + hospital.getNumMap().get("扁鹊") + "," + hospital.getNumMap().get("孙思邈") + ","
                    + hospital.getNumMap().get("李时珍") + "," + hospital.getNumMap().get("希波克拉底") + ","
                    + hospital.getNumMap().get("盖伦");
        try(FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw)){
            bfw.write(str);
        }catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    // 将患者挂号信息存入文件
    public void writeRecords(){
        // 将列表转化为字符串
        String str = JSON.toJSONString(hospital.getRecordsList());
        // 将字符串写入文件
        File f = new File(getClass().getResource("RecordsFile.data").getPath());
        try(FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw)){
            bfw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将部门信息写入文件
    public void writeDepartment() {
        // 将列表转化为字符串
        String str = JSON.toJSONString(hospital.getDepartmentList());
        // 将字符串写入文件
        File f = new File(getClass().getResource("DepartmentList.data").getPath());
        try(FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw)){
            bfw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
