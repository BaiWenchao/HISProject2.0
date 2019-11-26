package logic.DataStructure;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Map;

import static userInterface.DoctorDiagnosis.diseases;

public class Algorithms {
    //单例模式：
    private static Algorithms instance;
    private Algorithms(){}
    public static synchronized Algorithms getInstance(){
        if(instance == null){
            instance = new Algorithms();
        }
        return instance;
    }

    // 构建医院单例：
    Hospital hospital = Hospital.getInstance();

    // 广度优先遍历疾病树，返回树视图
    public TreeItem<String> diseaseBFT(DiseaseTreeNode root) throws Exception {

        TreeItem<String> temp = new TreeItem<>(root.getDiseaseName());
        TreeItem<String> treeItem = temp;
        DiseaseTreeNode tempNode = root;

        MyQueue<DiseaseTreeNode> queue = new MyQueue<>();
        queue.insert(root);

        Map<DiseaseTreeNode, TreeItem<String>> map = new HashMap<>();
        map.put(root, temp);

        while(!queue.isEmpty()){
            tempNode = queue.remove();
            temp = map.get(tempNode);

            if(!tempNode.getChildList().isEmpty()){
                for(DiseaseTreeNode item : tempNode.getChildList()){
                    queue.insert(item);
                    TreeItem<String> stringTreeItem = new TreeItem<>(item.getDiseaseName());
                    temp.getChildren().add(stringTreeItem);
                    map.put(item, stringTreeItem);
                }
            }
        }

        return treeItem;
    }

    // 广度优先遍历疾病树，返回带选择框的树视图
    public CheckBoxTreeItem<String> diseaseBFTwithCheckBox(DiseaseTreeNode root) throws Exception {

        CheckBoxTreeItem<String> temp = new CheckBoxTreeItem<>(root.getDiseaseName());
        temp.setIndependent(true);
        CheckBoxTreeItem<String> treeItem = temp;
        treeItem.setIndependent(true);
        DiseaseTreeNode tempNode = root;

        MyQueue<DiseaseTreeNode> queue = new MyQueue<>();
        queue.insert(root);

        Map<DiseaseTreeNode, CheckBoxTreeItem<String>> map = new HashMap<>();
        map.put(root, temp);

        while(!queue.isEmpty()){
            tempNode = queue.remove();
            temp = map.get(tempNode);

            if(!tempNode.getChildList().isEmpty()){
                for(DiseaseTreeNode item : tempNode.getChildList()){
                    queue.insert(item);
                    CheckBoxTreeItem<String> stringTreeItem = new CheckBoxTreeItem<>(item.getDiseaseName());
                    stringTreeItem.setIndependent(true);
                    temp.getChildren().add(stringTreeItem);
                    map.put(item, stringTreeItem);
                }
            }
        }

        return treeItem;
    }

    // 遍历CheckBoxTreeItem
    public void diseaseBFTwithObservableList(CheckBoxTreeItem<String> root) throws Exception {
        diseases.clear();

        MyQueue<CheckBoxTreeItem<String>> queue = new MyQueue<>();
        CheckBoxTreeItem<String> tempItem;
        queue.insert(root);

        while(!queue.isEmpty()){
            tempItem = queue.remove();
            if(tempItem.isSelected()){
                Disease d = hospital.getDiseaseTree().getNodeMap().get(tempItem.getValue()).getDisease();
                diseases.add(d);
            }

            if(!tempItem.getChildren().isEmpty()){
                for(TreeItem<String> item : tempItem.getChildren()){
                    queue.insert((CheckBoxTreeItem<String>)item);
                }
            }
        }
    }

    // 遍历疾病树返回疾病列表
    public ObservableList<ShowPatientUnderDisease> diseaseBFTwithDiseaseList(String root) throws Exception {
        ObservableList<ShowPatientUnderDisease> diseaseObservableList = FXCollections.observableArrayList();

        DiseaseTreeNode temp = hospital.getDiseaseTree().getNodeMap().get(root);
        MyQueue<DiseaseTreeNode> queue = new MyQueue<>();

        queue.insert(temp);

        while(!queue.isEmpty()){
            DiseaseTreeNode item = queue.remove();
            if(!item.getPatientList().isEmpty()){
                for(Patient p : item.getPatientList()){
                    ShowPatientUnderDisease s = new ShowPatientUnderDisease();
                    s.setDiseaseName(item.getDiseaseName());
                    s.setPatientName(p.getName());
                    s.setRecordNum(p.getHosRecordNum());
                    diseaseObservableList.add(s);
                }
            }

            if(!item.getChildList().isEmpty()){
                for(DiseaseTreeNode d : item.getChildList()){
                    queue.insert(d);
                }
            }
        }

        return diseaseObservableList;
    }
}
