package userInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.Show;

import java.io.IOException;

public class Entrance {

    //build an object of logic.Show
    Show show = Show.getInstance();

    @FXML
    private Button employeeButton;

    @FXML
    private Button patientButton;

    @FXML
    private void turnToPatientLogin() throws IOException {
        Parent root = FXMLLoader.load(Entrance.class.getResource("PatientLogin.fxml"));
        show.turnToStage(root,600,400);
    }

    @FXML
        private void turnToDoctorLogin() throws IOException {
        Parent root = FXMLLoader.load(Entrance.class.getResource("DoctorLogin.fxml"));
        show.turnToStage(root,600,400);
    }
}
