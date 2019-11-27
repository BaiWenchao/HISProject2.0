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


        //将医生队列中的患者按优先级插入队列
        future.clear();
        int sizeA = hospital.getDoctorList().get(docIndex).getFutureQueue().size();
        int countA = 0;
        int sizeB = hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().size();
        int countB = 0;

        String typeA = null;
        String typeB = null;

        while((countA < sizeA) || (countB < sizeB)){
            //处理A类队列已经扔完
            if(countA >= sizeA){
                future.add(hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().get(countB++));
                continue;
            }
            //处理B类队列已经扔完
            if(countB >= sizeB){
                future.add(hospital.getDoctorList().get(docIndex).getFutureQueue().get(countA++));
                continue;
            }
            //处理AB队列都没扔完
            typeA = hospital.getDoctorList().get(docIndex).getFutureQueue().get(countA).getCurrentRecordNum().substring(0,1);
            typeB = hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().get(countB).getCurrentRecordNum().substring(0,1);
            //AB两个队列都有加急患者
            if(typeA.equals("C") && typeB.equals("D")){
                future.add(hospital.getDoctorList().get(docIndex).getFutureQueue().get(countA++));
                future.add(hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().get(countB++));
            }else if(typeA.equals("C")){
                // A有加急患者，B没有
                // 将A类加急患者全部扔进列表
                while(typeA.equals("C")){
                    future.add(hospital.getDoctorList().get(docIndex).getFutureQueue().get(countA++));
                    typeA = hospital.getDoctorList().get(docIndex).getFutureQueue().get(countA).getCurrentRecordNum().substring(0,1);
                }
            }else if(typeB.equals("D")){
                // B有加急患者A没有
                // 将B类加急患者全部扔进列表
                while(typeB.equals("D")){
                    future.add(hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().get(countB++));
                    typeB = hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().get(countB).getCurrentRecordNum().substring(0,1);
                }
            }else{
                // AB都没有加急患者
                future.add(hospital.getDoctorList().get(docIndex).getFutureQueue().get(countA++));
                future.add(hospital.getDoctorList().get(docIndex).getReDiagnosisQueue().get(countB++));
            }
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
