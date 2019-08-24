package entity;

import java.util.ArrayList;
import java.util.List;

public class Diagnosis{
    private String describe;
    private String history;
    private String examine;
    private String advice;
    private List<Disease> diseaseList = new ArrayList<>();

    public Diagnosis() {
    }

    public Diagnosis(String describe, String history, String examine, String advice, List<Disease> diseaseList) {
        this.describe = describe;
        this.history = history;
        this.examine = examine;
        this.advice = advice;
        this.diseaseList = diseaseList;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }
}

