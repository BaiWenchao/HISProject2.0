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

    public boolean removeNode(String nodeName, String fatherName){
        if(nodeMap.get(nodeName) == null){
            return false;
        }

        nodeMap.get(fatherName).getChildList().remove(nodeMap.get(nodeName));

        if(nodeMap.get(nodeName).getChildList().isEmpty()){
            nodeMap.remove(nodeName);
        }else{
            List<DiseaseTreeNode> list = nodeMap.get(nodeName).getChildList();
            nodeMap.remove(nodeName);
            for(DiseaseTreeNode d : list){
                removeNode(d.getDiseaseName(), nodeName);
            }
        }

        return true;
    }



}
