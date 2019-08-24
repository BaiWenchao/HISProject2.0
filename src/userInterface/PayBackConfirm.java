package userInterface;

import entity.Medicine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PayBackConfirm {
    @FXML
    public Button confirm;

    @FXML
    public Label totalPrice;

    @FXML
    public TableColumn<Medicine, String> prjNum;

    @FXML
    public TableColumn<Medicine, String> prjName;

    @FXML
    public TableView<Medicine> prjTable;
}
