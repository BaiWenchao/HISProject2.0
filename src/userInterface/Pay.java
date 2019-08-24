package userInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.Util;

import static userInterface.PatientRegist.registFee;

public class Pay {
    //创建Util单例
    Util util = Util.getInstance();


    @FXML
    public Button pay;

    @FXML
    public Label howMuch;

}
