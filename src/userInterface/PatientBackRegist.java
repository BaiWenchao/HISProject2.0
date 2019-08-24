package userInterface;

import entity.Doctor;
import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Calculate;
import logic.Util;

import static userInterface.PatientLogin.patientHosRecordNum;
import static userInterface.PatientLogin.patientName;

public class PatientBackRegist {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建calculate单例
    Calculate calculate = Calculate.getInstance();

    //创建util单例
    Util util = Util.getInstance();

    @FXML
    private Button confirm;

    @FXML
    private Button fresh;

    @FXML
    private Label doctor;

    @FXML
    private Label date;

    @FXML
    private Label isExpert;

    @FXML
    private Label hosRecordNum;

    @FXML
    private Label totalFee;

    @FXML
    private Label name;

    @FXML
    private Label department;

    @FXML
    private Label recordNum;

    @FXML
    private Label needBook;

    @FXML
    private void initialize(){
        hosRecordNum.setText(patientHosRecordNum);
        name.setText(patientName);
    }

    @FXML
    private void showData(){
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(patientHosRecordNum)){
                recordNum.setText(p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getRecordNum());
                date.setText(p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getDate());
                department.setText(p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getDepartment());
                isExpert.setText(p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getIsExpert());
                doctor.setText(p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getDoctor());
                if(p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().isNeedRecordBook()){
                    needBook.setText("有");
                }else{
                    needBook.setText("无");
                }

                //计算金额
                boolean book = p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().isNeedRecordBook();
                String haobie = p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getIsExpert();
                totalFee.setText(calculate.registCalculate(book,haobie));
            }
        }
    }

    @FXML
    private void submit(){
        //从医师的未诊患者列表中移除
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(patientHosRecordNum)){
                String docName = p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getDoctor();
                for(Doctor d : hospital.getDoctorList()){
                    if(d.getName().equals(docName)){
                        for(int i=0; i<d.getFutureList().size(); i++){
                            if(d.getFutureList().get(i).getHosRecordNum().equals(patientHosRecordNum)){
                                d.getFutureList().remove(i);
                            }
                        }
                    }
                }
            }
        }

        //跳出提示信息
        util.completeInformationAlert("退号成功！");
    }
}
