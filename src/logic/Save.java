package logic;

import entity.Doctor;
import entity.Hospital;
import entity.Patient;
import entity.PatientData;

public class Save {

    //单例模式：
    private static Save instance;
    private Save(){}
    public static synchronized Save getInstance(){
        if(instance == null){
            instance = new Save();
        }
        return instance;
    }

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    public void registInfoSave(String hosRecordNum, String recordNum, boolean needRecordBook, String date, String department, String isExpert, String doctor, String wayToPay){
        //新建patientData，将患者挂号信息存入
        PatientData patientData = new PatientData();
        patientData.getRegist().setRecordNum(recordNum);
        patientData.getRegist().setNeedRecordBook(needRecordBook);
        patientData.getRegist().setDate(date);
        patientData.getRegist().setDepartment(department);
        patientData.getRegist().setIsExpert(isExpert);
        patientData.getRegist().setDoctor(doctor);
        patientData.getRegist().setWayToPay(wayToPay);

        //将patientData存入该患者,并把该患者挂号至相应医师
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(hosRecordNum)){

                p.getPatientDataList().add(patientData);
                // 刷新该患者当前病历号
                p.setCurrentRecordNum(recordNum);
                p.setCurrentDoctor(doctor);

                /*//将该患者加入对应医生的未诊患者列表
                for(Doctor d : hospital.getDoctorList()){
                    if(d.getName().equals(doctor)){
                        d.getFutureQueue().insert(p);
                    }
                }*/
            }
        }
    }
}
