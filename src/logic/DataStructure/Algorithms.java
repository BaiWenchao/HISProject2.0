package logic.DataStructure;

import entity.Disease;
import entity.DiseaseTree;
import entity.DiseaseTreeNode;
import javafx.scene.control.TreeItem;
import logic.DataStructure.MyQueue;

import java.util.HashMap;
import java.util.Map;

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
}
