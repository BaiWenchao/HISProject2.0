package userInterface;

import entity.Hospital;
import entity.Medicine;
import entity.Patient;
import entity.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import logic.Show;
import logic.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static userInterface.PatientLogin.patientHosRecordNum;
import static userInterface.PatientLogin.patientName;

public class PatientPayBack {

    //创建药品列表
    private ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();
    //创建退费药品列表
    private ObservableList<Medicine> medicines = FXCollections.observableArrayList();

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建Util单例
    Util util = Util.getInstance();

    //创建Show单例
    Show show = Show.getInstance();

    @FXML
    private Button submit;

    @FXML
    private Button fresh;

    @FXML
    private Label hosRecordNum;

    @FXML
    private TableView<Medicine> payBackTable;

    @FXML
    private Label name;

    @FXML
    private TableColumn<Medicine, String> projectNum;

    @FXML
    private TableColumn<Medicine, CheckBox> choice;

    @FXML
    private TableColumn<Medicine, String> projectName;

    @FXML
    private TableColumn<Medicine, String> projectPrice;

    private AnchorPane pane;

    private double fee = 0;

    @FXML
    private void initialize(){
        hosRecordNum.setText(patientHosRecordNum);
        name.setText(patientName);

        payBackTable.setItems(medicineObservableList);

        choice.setCellValueFactory(cellData -> cellData.getValue().getMyCheckBox().getCheckBox());
        projectName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        projectNum.setCellValueFactory(cellData -> cellData.getValue().med_numProperty());
        projectPrice.setCellValueFactory(cellData -> cellData.getValue().med_priceProperty());
    }

    @FXML
    private void showData(){
        for(Patient p : hospital.getPatientList()){
            medicineObservableList.clear();
            if(p.getHosRecordNum().equals(patientHosRecordNum)){
                if(p.getPatientDataList().get(p.getPatientDataList().size()-1).isHasPay()){
                    if(!p.getPatientDataList().get(p.getPatientDataList().size()-1).isGetMedicine()){
                        for(Prescription item : p.getPatientDataList().get(p.getPatientDataList().size()-1).getPrescriptionList()){
                            for(Medicine m : item.getMedicineList()){
                                medicineObservableList.add(m);
                            }
                        }
                    }else{
                        util.errorInformationAlert("药房已发药，不可退费！");
                    }
                }else{
                    util.errorInformationAlert("您还未缴费呢！");
                }
            }
        }
    }

    @FXML
    private void submit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PayBackConfirm.fxml"));

        pane = loader.load();
        show.turnToStage(pane, 800, 600);

        //初始化退费药品列表
        fee = 0;
        medicines.clear();
        for(Medicine m : medicineObservableList){
            if(m.getMyCheckBox().isSelected()){
                medicines.add(m);
            }
        }

        //get controller
        PayBackConfirm controller = loader.getController();

        //初始化列表
        controller.prjTable.setItems(medicines);
        controller.prjName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        controller.prjNum.setCellValueFactory(cellData -> cellData.getValue().med_numProperty());

        //计算总金额
        for(Medicine m : medicines){
            int lastFee = m.getMed_price().length()-1;
            int lastNum = m.getMed_num().length()-1;
            String tempFee = m.getMed_price().substring(0,lastFee);
            String tempNum = m.getMed_num().substring(0,lastNum);
            fee += Double.parseDouble(tempFee) * Integer.parseInt(tempNum);
        }

        controller.totalPrice.setText(String.valueOf(fee));


        controller.confirm.setOnAction((ActionEvent e) -> {

            //将退掉的药品从患者信息中移除
            for(Patient p : hospital.getPatientList()){
                if(p.getHosRecordNum().equals(hosRecordNum.getText())){
                    for(Prescription item : p.getPatientDataList().get(p.getPatientDataList().size()-1).getPrescriptionList()){
                        for(int i=0; i<item.getMedicineList().size(); i++){
                            for(int j=0; j<medicines.size(); j++){
                                if(item.getMedicineList().get(i).getMed_name().equals(medicines.get(j).getMed_name()) && item.getMedicineList().get(i).getMed_num().equals(medicines.get(j).getMed_num())){
                                    medicineObservableList.remove(medicines.get(j));
                                    item.getMedicineList().remove(i--);
                                    medicines.remove(j--);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            controller.totalPrice.setText("");

            util.completeInformationAlert("退费成功！");
            fee = 0;
        });


    }
}
