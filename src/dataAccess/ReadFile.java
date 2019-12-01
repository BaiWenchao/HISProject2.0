package dataAccess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import entity.*;
import javafx.scene.control.TreeItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    //单例模式：
    private static ReadFile instance;
    private ReadFile(){}
    public static synchronized ReadFile getInstance(){
        if(instance == null){
            instance = new ReadFile();
        }
        return instance;
    }

    //创建医院的单例：
    Hospital hospital = Hospital.getInstance();

    //读取科室信息：
    public void readDepartmentFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("Department.txt").getPath()));
            String line = reader.readLine();
            while(line != null){
                hospital.getDepartmentList().add(new Department(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取医生信息：
    public void readDoctorFile(){
        BufferedReader reader;
        {
            try {
                reader = new BufferedReader(new FileReader(getClass().getResource("Doctor.txt").getPath()));
                String line = reader.readLine();
                while(line != null){
                    String[] str = line.split(",");
                    Doctor doctor = new Doctor(str[0], str[1], str[2], str[3]);
                    hospital.getDoctorList().add(doctor);
                    //在对应部门中加入该医生
                    for(Department d : hospital.getDepartmentList()){
                        if(d.getName().equals(str[1])){
                            d.getDoctorList().add(doctor);
                        }
                    }

                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //读取疾病信息
    public void readDissaseFile(){
        BufferedReader reader;
        {
            try {
                reader = new BufferedReader(new FileReader(getClass().getResource("Disease.txt").getPath()));
                String line = reader.readLine();
                while(line != null){
                    String[] str = line.split(",");
                    hospital.getDiseaseList().add(new Disease(str[0],str[1]));
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取药品信息
    public void readDrugFile(){
        BufferedReader reader;
        {
            try {
                reader = new BufferedReader(new FileReader(getClass().getResource("Drug.txt").getPath()));
                String line = reader.readLine();
                while(line != null){
                    String[] str = line.split(",");
                    hospital.getMedicineList().add(new Medicine(str[0],str[1],str[2]));
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //将药品信息读入药房
    public void readMedicineInPharmacy(){
        BufferedReader reader;
        {
            try {
                reader = new BufferedReader(new FileReader(getClass().getResource("Drug.txt").getPath()));
                String line = reader.readLine();
                while(line != null){
                    String[] str = line.split(",");
                    hospital.getPharmacy().getMedicineStringMap().put(new Medicine(str[0], str[1], str[2]), str[3]);
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取处方模板
    public List<Prescription> readPrescriptionTempLate(){
        File f = new File(getClass().getResource("PrescriptionList.data").getPath());
        String str = null;
        try(FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr)){
            str = bfr.readLine();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Prescription> prescriptionList = JSON.parseArray(str,Prescription.class);
        return prescriptionList;
    }


    //读取病人列表
    public List<Patient> readPatientList(){
        File f = new File(getClass().getResource("PatientList.data").getPath());
        String str = null;
        try(FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr)){
            str = bfr.readLine();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Patient> patientList = JSON.parseArray(str,Patient.class);
        return patientList;
    }

    //读取医生列表
    public List<Doctor> readDoctorList(){
        File f = new File(getClass().getResource("DoctorList.data").getPath());
        String str = null;
        try(FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr)){
            str = bfr.readLine();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        List<Doctor> doctorList = JSON.parseArray(str,Doctor.class);
        return doctorList;
    }

    //读取疾病树
    public DiseaseTree readDiseaseTree(){
        File f = new File(getClass().getResource("DiseaseTree.data").getPath());
        String str = null;
        try(FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr)){
            str = bfr.readLine();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        /*DiseaseTree diseaseTree = JSON.parseObject(str, DiseaseTree.class);*/

        // 策略2：将读取的ArrayList塞回map，并返回
        List<DiseaseTreeNode> nodeList = JSON.parseArray(str, DiseaseTreeNode.class);
        DiseaseTree diseaseTree = new DiseaseTree();
        for(DiseaseTreeNode d : nodeList){
            diseaseTree.getNodeMap().put(d.getDiseaseName(), d);
        }
        return diseaseTree;
    }

    // 读取当天挂号数
    public void readRecordNum(){
        BufferedReader reader;
        {
            try{
                reader = new BufferedReader(new FileReader(getClass().getResource("recordNum.txt").getPath()));
                String s = reader.readLine();
                if(s != null){
                    String[] str = s.split(",");
                    hospital.setRecordNum(Integer.parseInt(str[0]));
                    hospital.setHtNum(Integer.parseInt(str[1]));
                    hospital.setBqNum(Integer.parseInt(str[2]));
                    hospital.setSsmNum(Integer.parseInt(str[3]));
                    hospital.setLszNum(Integer.parseInt(str[4]));
                    hospital.setXbNum(Integer.parseInt(str[5]));
                    hospital.setGlNum(Integer.parseInt(str[6]));
                    hospital.getNumMap().clear();
                    hospital.getNumMap().put("华佗",Integer.parseInt(str[1]));
                    hospital.getNumMap().put("扁鹊",Integer.parseInt(str[2]));
                    hospital.getNumMap().put("孙思邈",Integer.parseInt(str[3]));
                    hospital.getNumMap().put("李时珍",Integer.parseInt(str[4]));
                    hospital.getNumMap().put("希波克拉底",Integer.parseInt(str[5]));
                    hospital.getNumMap().put("盖伦",Integer.parseInt(str[6]));
                }else{
                    hospital.setRecordNum(0);
                    hospital.setHtNum(0);
                    hospital.setBqNum(0);
                    hospital.setSsmNum(0);
                    hospital.setLszNum(0);
                    hospital.setXbNum(0);
                    hospital.setGlNum(0);
                }
                reader.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    // 读取患者诊断记录
    public List<Records> readRecords(){
        File f = new File(getClass().getResource("RecordsFile.data").getPath());
        String str = null;
        try(FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr)){
            str = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Records> records = JSON.parseArray(str,Records.class);
        return records;
    }

    // 读取部门信息
    public List<Department> readDepartment(){
        File f = new File(getClass().getResource("DepartmentList.data").getPath());
        String str = null;
        try(FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr)){
            str = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Department> departments = JSON.parseArray(str,Department.class);
        return departments;
    }
}
