package logic;

import entity.Medicine;
import entity.Prescription;

import java.util.List;

public class Calculate {

    //单例模式：
    private static Calculate instance;
    private Calculate(){}
    public static synchronized Calculate getInstance(){
        if(instance == null){
            instance = new Calculate();
        }
        return instance;
    }

    public String registCalculate(boolean needRecordBook, String isExpert){
        int fee = 0;
        if(needRecordBook){
            fee += 1;
        }
        if(isExpert.equals("专家号")){
            fee += 70;
        }else{
            fee += 50;
        }

        return String.valueOf(fee)+"元";
    }

    public String prescriptionCalculate(List<Prescription> prescriptionList){
        double totalFee = 0;
        for(Prescription p : prescriptionList){
            for(Medicine m : p.getMedicineList()){
                totalFee += Double.parseDouble(m.getMed_price());
            }
        }
        return String.valueOf(totalFee)+"元";
    }
}
