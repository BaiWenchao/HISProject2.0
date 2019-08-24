package logic;

import entity.Hospital;
import entity.Patient;
import entity.PatientData;

public class PatientSignUpClass {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //单例模式：
    private static PatientSignUpClass instance;
    private PatientSignUpClass(){}
    public static synchronized PatientSignUpClass getInstance(){
        if(instance == null){
            instance = new PatientSignUpClass();
        }
        return instance;
    }

    public void patientSignUp(String num, String name, String gender, String birthday, String address, String phoneNum, String idNum){
        Patient patient = new Patient(num, name, gender, birthday, address, phoneNum, idNum);
        hospital.getPatientList().add(patient);
    }

}
