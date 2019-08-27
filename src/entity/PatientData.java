package entity;

import java.util.ArrayList;
import java.util.List;

public class PatientData {
    private Regist regist = new Regist();
    private Diagnosis diagnosis = new Diagnosis();
    private List<Prescription> prescriptionList = new ArrayList<>();
    private boolean getMedicine = false;
    private boolean hasPay = false;
    private boolean hasBackRegist = false;

    public PatientData() {
    }

    public Regist getRegist() {
        return regist;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setRegist(Regist regist) {
        this.regist = regist;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    public boolean isGetMedicine() {
        return getMedicine;
    }

    public void setGetMedicine(boolean getMedicine) {
        this.getMedicine = getMedicine;
    }

    public boolean isHasPay() {
        return hasPay;
    }

    public void setHasPay(boolean hasPay) {
        this.hasPay = hasPay;
    }

    public boolean isHasBackRegist() {
        return hasBackRegist;
    }

    public void setHasBackRegist(boolean hasBackRegist) {
        this.hasBackRegist = hasBackRegist;
    }
}

