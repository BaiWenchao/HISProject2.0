package userInterface;

import entity.Hospital;
import entity.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import logic.Show;

import java.io.IOException;

public class ShowMedicine {

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

    //创建药品列表
    ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

    //创建Show单例
    Show show = Show.getInstance();

    @FXML
    private Pagination myPagination;

    @FXML
    private TableColumn<Medicine, String> medicinePrice;

    @FXML
    private TableColumn<Medicine, String> medicineSpec;

    @FXML
    private TableColumn<Medicine, String> medicineName;

    @FXML
    private TableView<Medicine> medicineTable;

    private AnchorPane medicineDetail;

    @FXML
    private void initialize(){
        //初始化column
        medicineName.setCellValueFactory(cellData -> cellData.getValue().med_nameProperty());
        medicineSpec.setCellValueFactory(cellData -> cellData.getValue().med_specProperty());
        medicinePrice.setCellValueFactory(cellData -> cellData.getValue().med_priceProperty());

        //初始化分页控件
        int maxPageIndicatorCount;//导航页显示页数
        int pageCount;//总页数

        if(hospital.getMedicineList().size()%5 == 0){
            pageCount = hospital.getMedicineList().size()/5;
        }else{
            pageCount = hospital.getMedicineList().size()/5 + 1;
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
                showTable(param);
                return medicineTable;
            }
        });

        //设置表格元素的点击事件
        medicineTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MedicineDetail.fxml"));
                    try {
                        medicineDetail = loader.load();
                        MedicineDetail medDetController = loader.getController();

                        //MedicineDetail
                        medDetController.medName.setText(newValue.getMed_name());
                        medDetController.medSpec.setText(newValue.getMed_spec());
                        medDetController.medPrice.setText(newValue.getMed_price());

                        show.turnToStage(medicineDetail,800,600);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    private void showTable(Integer param){
        medicineObservableList.clear();

        int beginNum = param * 5;

        for(int i=0; i<5; i++){
            if(beginNum < hospital.getMedicineList().size()){
                medicineObservableList.add(hospital.getMedicineList().get(beginNum));
                ++beginNum;
            }
        }

        medicineTable.setItems(medicineObservableList);
    }
}
