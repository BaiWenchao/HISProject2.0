package logic.DataStructure;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;

import java.util.*;

import static userInterface.DoctorDiagnosis.diseases;
import static userInterface.DoctorLogin.future;

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

    public void mergeQueue(MyPriorityQueue<Patient> queue_1, MyPriorityQueue<Patient> queue_2){
        //将医生队列中的患者按优先级插入队列

        // d.getFutureQueue()是queue_1
        // d.getReDiagnosisQueue()是queue_2
        future.clear();
        int sizeA = queue_1.size();
        int countA = 0;
        int sizeB = queue_2.size();
        int countB = 0;

        String typeA = null;
        String typeB = null;

        while((countA < sizeA) || (countB < sizeB)){
            //处理A类队列已经扔完
            if(countA >= sizeA){
                future.add(queue_2.get(countB++));
                continue;
            }
            //处理B类队列已经扔完
            if(countB >= sizeB){
                future.add(queue_1.get(countA++));
                continue;
            }
            //处理AB队列都没扔完
            typeA = queue_1.get(countA).getCurrentRecordNum().substring(0,1);
            typeB = queue_2.get(countB).getCurrentRecordNum().substring(0,1);
            //AB两个队列都有加急患者
            if(typeA.equals("C") && typeB.equals("D")){
                future.add(queue_1.get(countA++));
                future.add(queue_2.get(countB++));
            }else if(typeA.equals("C")){
                // A有加急患者，B没有
                // 将A类加急患者全部扔进列表
                while(typeA.equals("C")){
                    future.add(queue_1.get(countA++));
                    typeA = queue_1.get(countA).getCurrentRecordNum().substring(0,1);
                }
            }else if(typeB.equals("D")){
                // B有加急患者A没有
                // 将B类加急患者全部扔进列表
                while(typeB.equals("D")){
                    future.add(queue_2.get(countB++));
                    typeB = queue_2.get(countB).getCurrentRecordNum().substring(0,1);
                }
            }else{
                // AB都没有加急患者
                future.add(queue_1.get(countA++));
                future.add(queue_2.get(countB++));
            }
        }
    }

    public void searchRecords_Common(ObservableList<Records> recordsObservableList, String hosRecordNum){
        for(Records r : hospital.getRecordsList()){
            if(r.getPatientID().equals(hosRecordNum)){
                recordsObservableList.add(r);
            }
        }
    }

    public void searchRecords_Binary(ObservableList<Records> recordsObservableList, String hosRecordNum){
        int targetNum = Integer.parseInt(hosRecordNum.substring(2));
        int left = 0;
        int right = hospital.getRecordsList().size() - 1;
        int mid;
        while(left <= right){
            mid = (left + right)/2;
            int midNum = Integer.parseInt(hospital.getRecordsList().get(mid).getPatientID().substring(2));
            if(midNum == targetNum){
                // 找到目标病案号
                    //搜索列表中目标病案号的第一位
                while((mid > 0) && (hospital.getRecordsList().get(mid).getPatientID().equals(hospital.getRecordsList().get(mid-1).getPatientID()))){
                    mid--;
                }
                while(hospital.getRecordsList().get(mid).getPatientID().equals(hosRecordNum)){
                    recordsObservableList.add(hospital.getRecordsList().get(mid));
                    mid++;
                    if(mid == hospital.getRecordsList().size()){
                        break;
                    }
                }
                break;

            }else if(midNum < targetNum){
                // 目标病案号在中值病案号右边
                left = mid + 1;
            }else{
                // 目标病案号在中值病案号左边
                right = mid - 1;
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high){
        if(arr.length <= 0) return;
        if(low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //挖坑1：保存基准的值
        while (left < right){
            while(left < right && arr[right] >= temp){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
    }

}
