package logic;

import entity.Hospital;
import entity.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.DataStructure.MyPriorityQueue;
import userInterface.DoctorRoot;

import java.io.IOException;
import java.util.List;

import static userInterface.DoctorLogin.past;
import static userInterface.DoctorLogin.future;

public class Show {
    //创建hospital单例
    Hospital hospital = Hospital.getInstance();

    //单例模式：
    private static Show instance;
    private Show(){}
    public static synchronized Show getInstance(){
        if(instance == null){
            instance = new Show();
        }
        return instance;
    }

    public void turnToStage(Parent root, int width, int height) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("东软云医院HIS系统");
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    public void showDoctorRoot(String docName, int docIndex, String department) throws IOException {

        future.clear();
        past.clear();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/userInterface/DoctorRoot.fxml"));
        AnchorPane a = (AnchorPane) loader.load();
        DoctorRoot dr = loader.getController();


        int size = hospital.getDoctorList().get(docIndex).getFutureQueue().size();
        for(int i=0; i<size; i++){
            future.add(hospital.getDoctorList().get(docIndex).getFutureQueue().get(i));
        }

        past.addAll(hospital.getDoctorList().get(docIndex).getPastList());

        dr.doctorName.setText(docName);
        dr.department.setText(department);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("东软云医院HIS系统");
        primaryStage.setScene(new Scene(a, 1500, 900));
        primaryStage.show();
    }



}
