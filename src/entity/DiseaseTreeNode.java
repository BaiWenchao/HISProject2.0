package entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiseaseTreeNode {
    /*private String diseaseName;
    private Map<String, String> patientMap = new TreeMap<>();
    private String diseaseCode;*/

    private Disease disease = new Disease();
    private List<DiseaseTreeNode> childList = new ArrayList<>();

    public DiseaseTreeNode() {
    }

    public DiseaseTreeNode(String diseaseName) {
        this.disease.setName(diseaseName);
    }

    public String getDiseaseName() {
        return this.disease.getName();
    }

    public void setDiseaseName(String diseaseName) {
        this.disease.setName(diseaseName);
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

    public List<Patient> getPatientList(){
        return this.disease.getPatientList();
    }

    public String getDiseaseCode() {
        return this.disease.getIcdCode();
    }

    public void setDiseaseCode(String diseaseCode) {
        this.disease.setIcdCode(diseaseCode);
    }
}
