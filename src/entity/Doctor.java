package entity;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private List<Patient> pastList = new ArrayList<>();
    private List<Patient> futureList = new ArrayList<>();

    private String name;
    private String department;
    private String level;//0代表专家，1代表普通

    public Doctor(String name, String department, String level) {
        this.name = name;
        this.department = department;
        this.level = level;
    }

    public Doctor() {
    }

    public List<Patient> getPastList() {
        return pastList;
    }

    public void setPastList(List<Patient> pastList) {
        this.pastList = pastList;
    }

    public List<Patient> getFutureList() {
        return futureList;
    }

    public void setFutureList(List<Patient> futureList) {
        this.futureList = futureList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
