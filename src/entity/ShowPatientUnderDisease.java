package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShowPatientUnderDisease {
    StringProperty diseaseName = new SimpleStringProperty();
    StringProperty recordNum = new SimpleStringProperty();
    StringProperty patientName = new SimpleStringProperty();

    public ShowPatientUnderDisease() {
    }

    public ShowPatientUnderDisease(StringProperty diseaseName, StringProperty recordNum, StringProperty patientName) {
        this.diseaseName = diseaseName;
        this.recordNum = recordNum;
        this.patientName = patientName;
    }

    public String getDiseaseName() {
        return diseaseName.get();
    }

    public StringProperty diseaseNameProperty() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName.set(diseaseName);
    }

    public String getRecordNum() {
        return recordNum.get();
    }

    public StringProperty recordNumProperty() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum.set(recordNum);
    }

    public String getPatientName() {
        return patientName.get();
    }

    public StringProperty patientNameProperty() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName.set(patientName);
    }
}
