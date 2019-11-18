package entity;

import javafx.scene.control.TreeItem;

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
    private Integer recordNum_A = null;
    private Integer recordNum_B = null;

    private DiseaseTree diseaseTree = new DiseaseTree();

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

    public Integer getRecordNum_A() {
        return recordNum_A;
    }

    public void setRecordNum_A(Integer recordNum_A) {
        this.recordNum_A = recordNum_A;
    }

    public Integer getRecordNum_B() {
        return recordNum_B;
    }

    public void setRecordNum_B(Integer recordNum_B) {
        this.recordNum_B = recordNum_B;
    }
}
