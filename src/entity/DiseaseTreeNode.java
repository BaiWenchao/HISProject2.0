package entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiseaseTreeNode {
    private String diseaseName;
    private Map<String, String> patientMap = new TreeMap<>();
    private String diseaseCode;
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

    public Map<String, String> getPatientMap() {
        return patientMap;
    }

    public void setPatientMap(Map<String, String> patientMap) {
        this.patientMap = patientMap;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }
}
