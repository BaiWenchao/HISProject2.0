package entity;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class DiseaseTreeNode {
    private String diseaseName;
    private List<DiseaseTreeNode> childList = new ArrayList<>();

    public DiseaseTreeNode() {
    }

    public DiseaseTreeNode(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public List<DiseaseTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<DiseaseTreeNode> childList) {
        this.childList = childList;
    }

    public void addChild(DiseaseTreeNode node){
        this.childList.add(node);
    }
}
