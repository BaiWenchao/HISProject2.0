package entity;

import java.util.HashMap;
import java.util.Map;

public class Pharmacy {
    //单例模式：
    private static Pharmacy instance;
    private Pharmacy(){}
    public static synchronized Pharmacy getInstance(){
        if(instance == null){
            instance = new Pharmacy();
        }
        return instance;
    }

    Map<Medicine,String> medicineStringMap = new HashMap<>();

    //存储药品及其储量的Map
    public Map<Medicine, String> getMedicineStringMap() {
        return medicineStringMap;
    }

    public void setMedicineStringMap(Map<Medicine, String> medicineStringMap) {
        this.medicineStringMap = medicineStringMap;
    }
}
