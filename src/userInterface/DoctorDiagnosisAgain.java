package userInterface;

import entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.DataStructure.Algorithms;
import logic.ReturnNum;
import logic.Show;
import static userInterface.DoctorDiagnosis.diseases;
import static userInterface.DoctorLogin.future;
import static userInterface.DoctorLogin.past;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDiagnosisAgain {
    //创建Show单例
    Show show = Show.getInstance();

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    // 创建数据结构算法类单例
    Algorithms algorithms = Algorithms.getInstance();

    // 创建ReturnNum单例
    ReturnNum returnNum = ReturnNum.getInstance();

    @FXML
    private TableColumn<Disease, String> diseaseName;

    @FXML
    private Button add;

    @FXML
    private Button submit;

    @FXML
    private Button delete;

    @FXML
    private TextArea advice;

    @FXML
    private TableColumn<Disease, String> icdCode;

    @FXML
    private TableView<Disease> diagnosis;

    @FXML
    public Label nameLabel;

    @FXML
    public Label hosRecordNumLabel;

    @FXML
    public Label doctorLabel;

    @FXML
    public Label departmentLabel;

    AnchorPane root = new AnchorPane();

    @FXML
    private void initialize(){
        diagnosis.setItems(diseases);

        icdCode.setCellValueFactory(cellData -> cellData.getValue().icdCodeProperty());
        diseaseName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    @FXML
    private void showDiseaseTree() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowDiseaseTree.fxml"));
        root = loader.load();
        ShowDiseaseTree controller = loader.getController();
        //为新界面初始化部门
        controller.department.setText(departmentLabel.getText());
        //为新界面初始化疾病树
        //设置选择框
        controller.diseaseTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());
        controller.diseaseTreeView.setEditable(true);

        CheckBoxTreeItem<String> item = algorithms.diseaseBFTwithCheckBox(hospital.getDiseaseTree().getNodeMap().get(departmentLabel.getText()));
        controller.diseaseTreeView.setRoot(item);

        //跳转到新界面
        show.turnToStage(root, 800, 600);

        // 将选中药品添加到列表
        controller.addButton.setOnAction((ActionEvent e) -> {
            try {
                // 将病人加入静态的列表
                algorithms.diseaseBFTwithObservableList(item);
                // 将病人添加到相应疾病的疾病列表中
                for(Patient p : hospital.getPatientList()){
                    if(p.getHosRecordNum().equals(hosRecordNumLabel.getText())){
                        for(Disease d : diseases){
                            hospital.getDiseaseTree().getNodeMap().get(d.getName()).getDisease().getPatientList().add(p);
                        }
                    }
                }

                // 关闭该页面
                Stage stage = (Stage) controller.addButton.getScene().getWindow();
                stage.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void deleteDisease(){
        diseases.remove(diagnosis.getSelectionModel().getFocusedIndex());
    }

    @FXML
    private void submit(){

        for(Patient p : hospital.getPatientList()){
            if(p.getHosRecordNum().equals(hosRecordNumLabel.getText())){
                List<Disease> diseaseList = new ArrayList<>();
                diseaseList.addAll(diseases);
                Rediagnosis rediagnosis = new Rediagnosis(diseaseList, advice.getText());
                p.getPatientDataList().get(p.getPatientDataList().size()-1).setRediagnosis(rediagnosis);

                //对患者列表信息进行增减
                for(Doctor d : hospital.getDoctorList()){
                    if(d.getName().equals(doctorLabel.getText())){

                        // 将患者写入对应部门
                        for(Department de : hospital.getDepartmentList()){
                            if(de.getName().equals(d.getDepartment())){
                                p.setCurrentInsertTime(returnNum.returnTime());
                                de.getPatientList().add(p);
                            }
                        }


                        d.getReDiagnosisQueue().remove();

                        algorithms.mergeQueue(d.getFutureQueue(), d.getReDiagnosisQueue());

                        d.getPastList().add(p);
                        past.add(p);

                        break;
                    }
                }

                //将该患者该次病历中设置为已诊断
                p.getPatientDataList().get(p.getPatientDataList().size()-1).setHasDiagnosis(true);

                break;
            }
        }
    }
}
