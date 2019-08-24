package logic;

import entity.Hospital;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReturnNum {
    //单例模式：
    private static ReturnNum instance;
    private ReturnNum(){}
    public static synchronized ReturnNum getInstance(){
        if(instance == null){
            instance = new ReturnNum();
        }
        return instance;
    }

    public String returnHosRecordNum(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(date);
    }

    public String returnRecordNum(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("mmssSSS");
        return sdf.format(date);
    }
}
