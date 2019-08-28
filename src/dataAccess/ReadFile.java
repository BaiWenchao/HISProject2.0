package dataAccess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import entity.*;

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
                    Doctor doctor = new Doctor(str[0], str[1], str[2]);
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
}
