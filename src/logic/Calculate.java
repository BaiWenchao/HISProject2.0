package logic;

import entity.Doctor;
import entity.Hospital;
import entity.Medicine;
import entity.Prescription;

import java.util.Calendar;
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

    //创建医院单例
    Hospital hospital = Hospital.getInstance();

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

    public void isWork(){
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_WEEK);

        for(Doctor d : hospital.getDoctorList()){
            if(((date == 1)||(date == 3)||(date == 5)||(date == 7)) && d.getRule().equals("1010101")){
                d.setWork(true);
            }else if(((date == 1)||(date == 2)||(date == 4)||(date == 6)) && d.getRule().equals("1101010")){
                d.setWork(true);
            }else{
                d.setWork(false);
            }
        }
    }
}
