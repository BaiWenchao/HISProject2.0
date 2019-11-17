package entity;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseTree {

    private transient DiseaseTreeNode root = new DiseaseTreeNode("疾病");
    private Map<String, DiseaseTreeNode> nodeMap = new HashMap<>();

    public DiseaseTree() {
        this.nodeMap.put("疾病", root);
    }

    public DiseaseTreeNode getRoot() {
        return root;
    }

    public void setRoot(DiseaseTreeNode root) {
        this.root = root;
    }

    public Map<String, DiseaseTreeNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, DiseaseTreeNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public boolean addNode(String nodeName, String fatherName){

        try{
            DiseaseTreeNode newNode = new DiseaseTreeNode(nodeName);

            nodeMap.put(nodeName, newNode);
            nodeMap.get(fatherName).addChild(newNode);



            return true;

        }catch (Exception e){
            return false;
        }
    }

}
