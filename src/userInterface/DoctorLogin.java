package userInterface;

import entity.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.Show;
import logic.Util;

import java.io.IOException;

public class DoctorLogin {

    public static ObservableList<Patient> future = FXCollections.observableArrayList();
    public static ObservableList<Patient> past = FXCollections.observableArrayList();

    @FXML
    private PasswordField code;

    @FXML
    private TextField name;

    @FXML
    private Button login;

    @FXML
    private Button joke;

    //build an object of logic.Show
    Show show = Show.getInstance();

    //建立Util单例
    Util util = Util.getInstance();

    //忘记密码
    @FXML
    private void turnToJoke() throws IOException {
        Parent root = FXMLLoader.load(Entrance.class.getResource("Joke.fxml"));
        show.turnToStage(root,600,400);
    }

    //医生登录
    @FXML
    private void turnToDoctorRoot() throws IOException {
        util.docLogin(name.getText(), code.getText());
    }
}
