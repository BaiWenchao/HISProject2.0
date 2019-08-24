package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.security.ssl.SSLContextImpl;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private StringProperty hosRecordNum = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty gender = new SimpleStringProperty();
    private StringProperty birthday = new SimpleStringProperty();
    private StringProperty homeAddress = new SimpleStringProperty();
    private StringProperty phoneNum = new SimpleStringProperty();
    private StringProperty idNum = new SimpleStringProperty();
    private List<PatientData> patientDataList = new ArrayList<>();

    public Patient() {
    }

    public Patient(String hosRecordNum, String name, String gender, String birthday, String homeAddress, String phoneNum, String idNum) {
        this.hosRecordNum.set(hosRecordNum);
        this.name.set(name);
        this.gender.set(gender);
        this.birthday.set(birthday);
        this.homeAddress.set(homeAddress);
        this.phoneNum.set(phoneNum);
        this.idNum.set(idNum);
    }

    public List<PatientData> getPatientDataList() {
        return patientDataList;
    }

    public void setPatientDataList(List<PatientData> patientDataList) {
        this.patientDataList = patientDataList;
    }

    public String getHosRecordNum() {
        return hosRecordNum.get();
    }

    public StringProperty hosRecordNumProperty() {
        return hosRecordNum;
    }

    public void setHosRecordNum(String hosRecordNum) {
        this.hosRecordNum.set(hosRecordNum);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getHomeAddress() {
        return homeAddress.get();
    }

    public StringProperty homeAddressProperty() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress.set(homeAddress);
    }

    public String getPhoneNum() {
        return phoneNum.get();
    }

    public StringProperty phoneNumProperty() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum.set(phoneNum);
    }

    public String getIdNum() {
        return idNum.get();
    }

    public StringProperty idNumProperty() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum.set(idNum);
    }
}
