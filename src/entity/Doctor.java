package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.DataStructure.MyHeap;
import logic.DataStructure.MyPriorityQueue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Doctor {
    private List<Patient> pastList = new ArrayList<>();

    private MyPriorityQueue<Patient> futureQueue = new MyPriorityQueue<>();
    private MyPriorityQueue<Patient> reDiagnosisQueue = new MyPriorityQueue<>();

    private StringProperty name = new SimpleStringProperty();
    private StringProperty department = new SimpleStringProperty();
    private StringProperty level = new SimpleStringProperty();//0代表专家，1代表普通

    //排号规则及是否排号
    private StringProperty rule = new SimpleStringProperty();
    private boolean isWork = false;

    public Doctor(String name, String department, String level) {
        this.name.set(name);
        this.department.set(department);
        this.level.set(level);
    }

    public Doctor() {

    }

    public List<Patient> getPastList() {
        return pastList;
    }

    public void setPastList(List<Patient> pastList) {
        this.pastList = pastList;
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

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getLevel() {
        return level.get();
    }

    public StringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public String getRule() {
        return rule.get();
    }

    public StringProperty ruleProperty() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule.set(rule);
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    public MyPriorityQueue<Patient> getFutureQueue() {
        return futureQueue;
    }

    public void setFutureQueue(MyPriorityQueue<Patient> futureQueue) {
        this.futureQueue = futureQueue;
    }

    public MyPriorityQueue<Patient> getReDiagnosisQueue() {
        return reDiagnosisQueue;
    }

    public void setReDiagnosisQueue(MyPriorityQueue<Patient> reDiagnosisQueue) {
        this.reDiagnosisQueue = reDiagnosisQueue;
    }
}
