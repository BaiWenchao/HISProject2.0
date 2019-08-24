package entity;

public class Regist{
    private String recordNum;
    private boolean needRecordBook;
    private String date;
    private String department;
    private String isExpert;
    private String doctor;
    private String wayToPay;

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
    }

    public boolean isNeedRecordBook() {
        return needRecordBook;
    }

    public void setNeedRecordBook(boolean needRecordBook) {
        this.needRecordBook = needRecordBook;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(String isExpert) {
        this.isExpert = isExpert;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getWayToPay() {
        return wayToPay;
    }

    public void setWayToPay(String wayToPay) {
        this.wayToPay = wayToPay;
    }

}

