package entity;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    //单例模式：
    private static Hospital instance;
    private Hospital(){}
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
}
