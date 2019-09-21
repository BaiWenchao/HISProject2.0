package userInterface;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Show;
import logic.Util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static userInterface.PatientLogin.patientHosRecordNum;
import static userInterface.PatientLogin.patientName;

public class PatientPay {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();
    //创建药房单例
    Pharmacy pharmacy = Pharmacy.getInstance();
    //创建Show单例
    Show show = Show.getInstance();
    //创建Util单例
    Util util = Util.getInstance();

    //创建药品列表
    public ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

    @FXML
    private Button submit;

    @FXML
    private Button fresh;

    @FXML
    private Label hosRecordNum;

    @FXML
    private Label totalFee;

    @FXML
    private Label name;

    @FXML
    private TableView<Medicine> projectTable;

    @FXML
    private ChoiceBox<String> wayToPay;

    @FXML
    private TableColumn<Medicine, String> projectNum;

    @FXML
    private TableColumn<Medicine, String> projectName;

    @FXML
    private TableColumn<Medicine, String> projectPrice;

    private AnchorPane pay;

    @FXML
    private void initialize(){
        hosRecordNum.setText(patientHosRecordNum);
        name.setText(patientName);
    }

    @FXML
    private void showData(){

        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(patientHosRecordNum)){
                if(!p.getPatientDataList().get(p.getPatientDataList().size()-1).isHasPay()){
                    for(Prescription item : p.getPatientDataList().get(p.getPatientDataList().size()-1).getPrescriptionList()){
                        for(Medicine m : item.getMedicineList()){
                            medicineObservableList.add(m);
                        }
                    }
                }else{
                    util.errorInformationAlert("您已缴过费了！");
                }
            }
        }

        projectTable.setItems(medicineObservableList);
        projectName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        projectNum.setCellValueFactory(cellData -> cellData.getValue().med_numProperty());
        projectPrice.setCellValueFactory(cellData -> cellData.getValue().med_priceProperty());

        double Fee = 0;

        for(Medicine m : medicineObservableList){
            int last0 = m.getMed_price().length()-1;
            int last1 = m.getMed_num().length()-1;
            String str0 = m.getMed_price().substring(0,last0);
            String str1 = m.getMed_num().substring(0,last1);
            Fee += Double.parseDouble(str0) * Integer.parseInt(str1);
        }

        totalFee.setText(String.valueOf(Fee));
    }

    @FXML
    private void initWayToPay(){
        ObservableList<String> choices = FXCollections.observableArrayList("微信支付","支付宝支付");
        wayToPay.setItems(choices);
    }


    @FXML
    private void pay() throws IOException {
        //把该患者hasPay设为true
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(patientHosRecordNum)){
                p.getPatientDataList().get(p.getPatientDataList().size()-1).setHasPay(true);
            }
        }

        //打开支付界面
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Pay.fxml"));
        pay = loader.load();
        Pay payController = loader.getController();
        payController.howMuch.setText(totalFee.getText());
        show.turnToStage(pay,500,400);

        payController.pay.setOnAction((ActionEvent e) -> {

             Stage stage = (Stage) payController.pay.getScene().getWindow();
             stage.close();

            //在药房中更新药品储量
            Set<Map.Entry<Medicine, String>> entries = pharmacy.getMedicineStringMap().entrySet();
            int tempNum = 0;
            int num = 0;
            int inputNum;
            int lastIndex;

            for(Medicine m : medicineObservableList){
                for(Map.Entry<Medicine, String> entry : entries){
                    if(entry.getKey().getMed_name().equals(m.getMed_name())){
                        //重新设置该药品的储量
                        tempNum = Integer.parseInt(entry.getValue());
                        lastIndex = m.getMed_num().length() - 1;
                        num = Integer.parseInt(m.getMed_num().substring(0,lastIndex));
                        inputNum = tempNum - num;
                        entry.setValue(String.valueOf(inputNum));
                    }
                }
            }

            //将药品列表清空
            medicineObservableList.clear();

            //将该患者从医生已诊患者列表中拿出
            for(Patient p : hospital.getPatientList()){
                if(p.getHosRecordNum().equals(hosRecordNum.getText())){
                    String name = p.getPatientDataList().get(p.getPatientDataList().size()-1).getRegist().getDoctor();
                    for(Doctor d : hospital.getDoctorList()){
                        if(d.getName().equals(name)){
                            for(int i=0; i<d.getPastList().size(); i++){
                                if(d.getPastList().get(i).getHosRecordNum().equals(hosRecordNum.getText())){
                                    d.getPastList().remove(i);
                                }
                            }
                        }
                    }
                }
            }

            //显示提示信息
            util.completeInformationAlert("缴费成功！");
        });

    }
}
