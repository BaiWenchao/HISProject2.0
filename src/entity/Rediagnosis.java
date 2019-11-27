package entity;

import java.util.ArrayList;
import java.util.List;

public class Rediagnosis {
    private List<Disease> diseaseList = new ArrayList<>();
    String advice;

    public Rediagnosis() {
    }

    public Rediagnosis(List<Disease> diseaseList, String advice) {
        this.diseaseList = diseaseList;
        this.advice = advice;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }


}
