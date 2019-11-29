package userInterface;

import entity.Doctor;
import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.DataStructure.Algorithms;
import logic.Util;

import static userInterface.DoctorLogin.future;

public class Nurse {

    // 创建医院单例
    Hospital hospital = Hospital.getInstance();
    // 床架Util单例
    Util util = Util.getInstance();
    // 创建算法类单例
    Algorithms algorithms = Algorithms.getInstance();

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
    private Label needReDiagnosis;

    @FXML
    private TableColumn<Patient, String> Name;

    @FXML
    private void initialize(){

    }

    @FXML
    private void setSearch(){
        // 显示病人信息，将病人初始化为A类
        String num = hosRecordNumInput.getText();
        boolean isFind = false;
        for(Patient p : hospital.getPatientList()){

            if(p.getHosRecordNum().equals(num)){
                // 处理未挂号就分诊的情况
                if(p.getCurrentRecordNum() == null){
                    util.errorInformationAlert("您还未挂号！");
                    return;
                }

                // 处理重复分诊
                if((p.getCurrentRecordNum().substring(0,1).equals("A") || p.getCurrentRecordNum().substring(0,1).equals("C")) && !p.isCurrentNeedRediagnosis()){
                    util.errorInformationAlert("您已在候诊队列！");
                    return;
                }

                // 显示待诊队列
                for(Doctor d : hospital.getDoctorList()){
                    if(d.getName().equals(p.getCurrentDoctor())){
                        // 将两个队列合并送入观察者列表
                        algorithms.mergeQueue(d.getFutureQueue(),d.getReDiagnosisQueue());
                    }
                }
                waitQueue.setItems(future);
                sequentNum.setCellValueFactory(cellData -> cellData.getValue().currentRecordNumProperty());
                name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());


                // 找到该患者
                isFind = true;
                // 显示病人基本挂号信息
                nameInfo.setText(p.getName());
                sexInfo.setText(p.getGender());
                docInfo.setText(p.getCurrentDoctor());

                // 显示患者是否初诊
                if(p.isCurrentNeedRediagnosis()){
                    needReDiagnosis.setText("否");
                }else{
                    needReDiagnosis.setText("是");
                }

            }
        }
        if(!isFind){
            util.errorInformationAlert("无此病案号！");
        }
    }

    @FXML
    private void setInsert(){
        if(docInfo.getText() == null){
            util.errorInformationAlert("请输入病案号！");
        }

        //先初始化患者序号，若设置为加急状态则修改当前候诊序号
        String num = hosRecordNumInput.getText();
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(num)){
                // 初始化患者序号
                if(p.isCurrentNeedRediagnosis()){
                    String s = "B" + hospital.getNumMap().get(docInfo.getText()).toString();
                    hospital.getNumMap().put(docInfo.getText(),hospital.getNumMap().get(docInfo.getText()) + 1);
                    p.setCurrentRecordNum(s);
                }else{
                    String s = "A" + hospital.getNumMap().get(docInfo.getText()).toString();
                    hospital.getNumMap().put(docInfo.getText(),hospital.getNumMap().get(docInfo.getText()) + 1);
                    p.setCurrentRecordNum(s);
                }

                if(isEmergency.isSelected()){
                    if(p.isCurrentNeedRediagnosis()){
                        // 将患者改为D类
                        p.setCurrentRecordNum("D" + p.getCurrentRecordNum().substring(1));
                    }else{
                        // 将病人改为C类
                        p.setCurrentRecordNum("C" + p.getCurrentRecordNum().substring(1));
                    }
                }
            }
        }

        // 将该患者插入医生的未诊队列
        for(Doctor d : hospital.getDoctorList()){
            if(d.getName().equals(docInfo.getText())){
                for(Patient p : hospital.getPatientList()){
                    if(p.getHosRecordNum().equals(hosRecordNumInput.getText())){

                        // 遍历future，判断是否将患者重复添加
                        for(Patient item : future){
                            if(item.getHosRecordNum().equals(p.getHosRecordNum()) && item.getCurrentRecordNum().equals(p.getCurrentRecordNum())){
                                util.errorInformationAlert("请勿重复添加！");
                                return;
                            }
                        }

                        if(p.isCurrentNeedRediagnosis()){
                            // 插入看诊队列
                            d.getReDiagnosisQueue().insert(p);
                        }else{
                            // 插入未诊队列
                            d.getFutureQueue().insert(p);
                        }

                        //将医生队列中的患者按优先级插入队列
                        algorithms.mergeQueue(d.getFutureQueue(), d.getReDiagnosisQueue());

                    }
                }
            }
        }
    }
}
