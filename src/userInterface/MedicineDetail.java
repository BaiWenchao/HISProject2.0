package userInterface;

import entity.Hospital;
import entity.Medicine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Util;

import static userInterface.DoctorMedicine.medicines;

public class MedicineDetail {
    //创建util单例
    Util util = Util.getInstance();
    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    @FXML
    private TextField medTime;

    @FXML
    private Button confirm;

    @FXML
    public TextField medName;

    @FXML
    private TextField medNum;

    @FXML
    private TextField medUse;

    @FXML
    public TextField medPrice;

    @FXML
    private TextField medAmount;

    @FXML
    public TextField medSpec;

    @FXML
    private TextField medUnit;

    @FXML
    private void submit(){
        // 处理药品数量不够的情况
        for(Medicine m : hospital.getPharmacy().getMedicineStringMap().keySet()){
            if(m.getMed_name().equals(medName.getText())){
                if(Integer.parseInt(hospital.getPharmacy().getMedicineStringMap().get(m)) < Integer.parseInt(medNum.getText())){
                    medNum.setText(hospital.getPharmacy().getMedicineStringMap().get(m));
                    util.errorInformationAlert("药品库存不足！");
                }
            }
        }

        String num = medNum.getText() + medUnit.getText();
        Medicine medicine = new Medicine(medName.getText(), medSpec.getText(), medUse.getText(), medAmount.getText(), medTime.getText(), medPrice.getText(), num);
        medicines.add(medicine);

        Stage stage = (Stage) medUnit.getScene().getWindow();
        stage.close();

        util.completeInformationAlert("药品已添加！");
    }
}
