package userInterface;

import entity.Doctor;
import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.Util;

import static userInterface.DoctorLogin.future;

public class Nurse {

    // 创建医院单例
    Hospital hospital = Hospital.getInstance();
    // 床架Util单例
    Util util = Util.getInstance();

    @FXML
    private RadioButton isEmergency;

    @FXML
    private Button search;

    @FXML
    private TableView<Patient> waitQueue;

    @FXML
    private TextField hosRecordNumInput;

    @FXML
    private Label sexInfo;

    @FXML
    private TableColumn<Patient, String> sequentNum;

    @FXML
    private TableColumn<Patient, String> name;

    @FXML
    private Button insert;

    @FXML
    private Label nameInfo;

    @FXML
    private Label docInfo;

    @FXML
    private TableColumn<Patient, String> Name;

    @FXML
    private void initialize(){
        waitQueue.setItems(future);
        sequentNum.setCellValueFactory(cellData -> cellData.getValue().currentRecordNumProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    @FXML
    private void setSearch(){
        // 显示病人信息，将病人初始化为A类
        String num = hosRecordNumInput.getText();
        boolean isFind = false;
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(num)){
                // 找到该患者
                isFind = true;
                // 显示病人基本挂号信息
                nameInfo.setText(p.getName());
                sexInfo.setText(p.getGender());
                docInfo.setText(p.getCurrentDoctor());
                // 将病人初始化为A类
                p.setCurrentRecordNum("A" + p.getCurrentRecordNum());
            }
        }
        if(!isFind){
            util.errorInformationAlert("无此病案号！");
        }
    }

    @FXML
    private void setEmergency(){
        if(hosRecordNumInput ==  null){
            util.errorInformationAlert("请输入病案号！");
        }
        if(isEmergency.isSelected()){
            String num = hosRecordNumInput.getText();
            for(Patient p : hospital.getPatientList()){
                if(p.getHosRecordNum().equals(num)){
                    // 将病人改为C类
                    p.setCurrentRecordNum("C" + p.getCurrentRecordNum().substring(1));
                }
            }
        }
    }

    @FXML
    private void setInsert(){
        if(docInfo.getText() == null){
            util.errorInformationAlert("请输入病案号！");
        }

        // 将该患者插入医生的未诊队列
        for(Doctor d : hospital.getDoctorList()){
            if(d.getName().equals(docInfo.getText())){
                for(Patient p : hospital.getPatientList()){
                    if(p.getHosRecordNum().equals(hosRecordNumInput.getText())){
                        // 插入未诊队列
                        d.getFutureQueue().insert(p);

                        // 插入观察者列表
                        future.clear();
                        int size = d.getFutureQueue().size();
                        for(int i=0; i<size; i++){
                            future.add(d.getFutureQueue().get(i));
                        }
                    }
                }
            }
        }
    }
}
