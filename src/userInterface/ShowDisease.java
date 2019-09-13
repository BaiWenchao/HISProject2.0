package userInterface;

import entity.Disease;
import entity.Hospital;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import static userInterface.DoctorDiagnosis.diseases;

public class ShowDisease {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建疾病的ObservableList
    ObservableList<Disease> diseaseObservableList = FXCollections.observableArrayList();

    @FXML
    private Button confirm;

    @FXML
    private TableColumn<Disease, String> ideCode;

    @FXML
    private TableColumn<Disease, String> name;

    @FXML
    private TableColumn<Disease, CheckBox> check;

    @FXML
    private TableView<Disease> table;

    @FXML
    private Pagination myPagination;

    @FXML
    private void initialize(){
        //初始化column
        ideCode.setCellValueFactory(cellData -> cellData.getValue().icdCodeProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        check.setCellValueFactory(cellData -> cellData.getValue().getMyCheckBox().getCheckBox());

        //初始化分页控件
        int maxPageIndicatorCount;//导航页显示页数
        int pageCount;//总页数

        if(hospital.getDiseaseList().size()%5 == 0){
            pageCount = hospital.getDiseaseList().size()/5;
        }else{
            pageCount = hospital.getDiseaseList().size()/5 + 1;
        }

        if(pageCount >= 10){
            maxPageIndicatorCount = 10;
        }else{
            maxPageIndicatorCount = pageCount;
        }

        myPagination.setPageCount(pageCount);
        myPagination.setMaxPageIndicatorCount(maxPageIndicatorCount);

        myPagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                showtable(param);
                return table;
            }
        });

    }

    private void showtable(Integer param){
        diseaseObservableList.clear();

        int beginNum = param * 5;

        for(int i=0; i<5; i++){
            if(beginNum < hospital.getDiseaseList().size()){
                diseaseObservableList.add(hospital.getDiseaseList().get(beginNum));
                ++beginNum;
            }
        }

        table.setItems(diseaseObservableList);
    }

    @FXML
    private void addDisease(){
        diseases.clear();
        for(Disease d : hospital.getDiseaseList()){
            if(d.getMyCheckBox().isSelected()){
                diseases.add(d);
            }
        }

        Stage stage = (Stage)confirm.getScene().getWindow();
        stage.close();
    }
}
