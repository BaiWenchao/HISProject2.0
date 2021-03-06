package entity;

import javafx.scene.control.TreeItem;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hospital {

    //单例模式：
    private static Hospital instance;
    private Hospital(){ }
    public static synchronized Hospital getInstance(){
        if(instance == null){
            instance = new Hospital();
        }
        return instance;
    }

    //获取药房单例
    Pharmacy pharmacy = Pharmacy.getInstance();

    private List<Patient> patientList = new ArrayList<>();
    private List<Doctor> doctorList = new ArrayList<>();
    private List<Department> departmentList = new ArrayList<>();
    private List<Disease> diseaseList = new ArrayList<>();
    private List<Medicine> medicineList = new ArrayList<>();
    public List<Prescription> prescriptionTemplateList = new ArrayList<>();
    private Integer recordNum = 0;
    private Integer htNum = 0;
    private Integer bqNum = 0;
    private Integer ssmNum = 0;
    private Integer lszNum = 0;
    private Integer xbNum = 0;
    private Integer glNum = 0;
    private Map<String, Integer> numMap = new HashMap<>();
    private DiseaseTree diseaseTree = new DiseaseTree();
    private List<Records> recordsList = new ArrayList<>();

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public List<Prescription> getPrescriptionTemplateList() {
        return prescriptionTemplateList;
    }

    public void setPrescriptionTemplateList(List<Prescription> prescriptionTemplateList) {
        this.prescriptionTemplateList = prescriptionTemplateList;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public DiseaseTree getDiseaseTree() {
        return diseaseTree;
    }

    public void setDiseaseTree(DiseaseTree diseaseTree) {
        this.diseaseTree = diseaseTree;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public Integer getHtNum() {
        return htNum;
    }

    public void setHtNum(Integer htNum) {
        this.htNum = htNum;
    }

    public Integer getBqNum() {
        return bqNum;
    }

    public void setBqNum(Integer bqNum) {
        this.bqNum = bqNum;
    }

    public Integer getSsmNum() {
        return ssmNum;
    }

    public void setSsmNum(Integer ssmNum) {
        this.ssmNum = ssmNum;
    }

    public Integer getLszNum() {
        return lszNum;
    }

    public void setLszNum(Integer lszNum) {
        this.lszNum = lszNum;
    }

    public Integer getXbNum() {
        return xbNum;
    }

    public void setXbNum(Integer xbNum) {
        this.xbNum = xbNum;
    }

    public Integer getGlNum() {
        return glNum;
    }

    public void setGlNum(Integer glNum) {
        this.glNum = glNum;
    }

    public Map<String, Integer> getNumMap() {
        return numMap;
    }

    public void setNumMap(Map<String, Integer> numMap) {
        this.numMap = numMap;
    }

    public List<Records> getRecordsList() {
        return recordsList;
    }

    public void setRecordsList(List<Records> recordsList) {
        this.recordsList = recordsList;
    }
}
