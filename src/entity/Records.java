package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Records implements Comparable<Records>{
    StringProperty patientID = new SimpleStringProperty();
    StringProperty docName = new SimpleStringProperty();
    StringProperty time = new SimpleStringProperty();
    List<Prescription> medicines = new ArrayList<>();
    Diagnosis diagnosis = new Diagnosis();
    Rediagnosis rediagnosis = new Rediagnosis();

    public Records() {
    }

    public Records(String patientID, String docName, String time, List<Prescription> medicines, Diagnosis diagnosis, Rediagnosis rediagnosis) {
        this.patientID.set(patientID);
        this.docName.set(docName);
        this.time.set(time);
        this.medicines = medicines;
        this.diagnosis = diagnosis;
        this.rediagnosis = rediagnosis;
    }

    public String getPatientID() {
        return patientID.get();
    }

    public StringProperty patientIDProperty() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID.set(patientID);
    }

    public String getDocName() {
        return docName.get();
    }

    public StringProperty docNameProperty() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName.set(docName);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public List<Prescription> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Prescription> medicines) {
        this.medicines = medicines;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Rediagnosis getRediagnosis() {
        return rediagnosis;
    }

    public void setRediagnosis(Rediagnosis rediagnosis) {
        this.rediagnosis = rediagnosis;
    }

    @Override
    public int compareTo(Records o) {
        int thisID = Integer.parseInt(this.getPatientID().substring(2));
        int otherID = Integer.parseInt(o.getPatientID().substring(2));

        if(thisID < otherID){
            return -1;
        }else{
            return 1;
        }
    }
}
