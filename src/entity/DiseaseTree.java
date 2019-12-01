package entity;

import logic.DataStructure.MyHashMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseTree{

    private transient DiseaseTreeNode root = new DiseaseTreeNode("疾病");
    private MyHashMap<String, DiseaseTreeNode> nodeMap = new MyHashMap<>();

    public DiseaseTree(DiseaseTreeNode root, MyHashMap<String, DiseaseTreeNode> nodeMap) {
        this.root = root;
        this.nodeMap = nodeMap;
    }

    public DiseaseTree() {
        this.nodeMap.put("疾病", root);
    }

    public DiseaseTreeNode getRoot() {
        return root;
    }

    public void setRoot(DiseaseTreeNode root) {
        this.root = root;
    }

    public MyHashMap<String, DiseaseTreeNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(MyHashMap<String, DiseaseTreeNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public boolean addNode(String nodeName, String fatherName, String code){

        try{
            DiseaseTreeNode newNode = new DiseaseTreeNode(nodeName);

            nodeMap.put(nodeName, newNode);
            nodeMap.get(fatherName).addChild(newNode);
            nodeMap.get(nodeName).setDiseaseCode(code);

            return true;

        }catch (Exception e){
            return false;
        }
    }



}
