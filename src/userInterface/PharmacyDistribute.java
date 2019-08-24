package userInterface;

import entity.Hospital;
import entity.Medicine;
import entity.Patient;
import entity.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.Util;

public class PharmacyDistribute {

    private ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建util单例
    Util util = Util.getInstance();

    @FXML
    private Button search;

    @FXML
    private Button submit;

    @FXML
    private TextField hosRecordNum;

    @FXML
    private TableColumn<Medicine, String> medicineName;

    @FXML
    private TableView<Medicine> medicineTable;

    @FXML
    private TableColumn<Medicine, String> medicineNum;

    @FXML
    private Label confirm;

    @FXML
    private void searchPatient(){
        //为药品列表加药，同时确认患者信息
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(hosRecordNum.getText())){
                confirm.setText(p.getName());
                if(!p.getPatientDataList().get(p.getPatientDataList().size()-1).isGetMedicine()){
                    for(Prescription item : p.getPatientDataList().get(p.getPatientDataList().size()-1).getPrescriptionList()){
                        for(Medicine m : item.getMedicineList()){
                            medicineObservableList.add(m);
                        }
                    }

                }
            }
        }
    }

    @FXML
    private void initialize(){
        medicineTable.setItems(medicineObservableList);
        medicineName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        medicineNum.setCellValueFactory(cellData -> cellData.getValue().med_numProperty());
    }

    @FXML
    private void submit(){
        medicineObservableList.clear();
        util.completeInformationAlert("药品发放成功！");
        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(hosRecordNum.getText())){
                p.getPatientDataList().get(p.getPatientDataList().size()-1).setGetMedicine(true);
            }
        }
    }
}
