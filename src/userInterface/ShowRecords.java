package userInterface;

import entity.Records;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShowRecords {
    @FXML
    public TableColumn<Records, String> numColumn;

    @FXML
    public TableColumn<Records, String> timeColumn;

    @FXML
    public TableColumn<Records, String> docColumn;

    @FXML
    public TableView<Records> recordTable;
}
