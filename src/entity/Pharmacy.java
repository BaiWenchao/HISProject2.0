package entity;

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
}
