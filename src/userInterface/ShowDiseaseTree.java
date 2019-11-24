package userInterface;

import entity.DiseaseTreeNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import logic.DataStructure.Algorithms;

public class ShowDiseaseTree {
    @FXML
    public TreeView<String> diseaseTreeView;

    @FXML
    public Button addButton;

    @FXML
    public Label department;

}
