package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

class DateUtil{
//    public static SimpleDateFormat sdf;
    private static ThreadLocal<SimpleDateFormat> st = new ThreadLocal<SimpleDateFormat>(){
    @Override
    protected SimpleDateFormat initialValue() {
        System.out.println("test");
        return new SimpleDateFormat("yyy/MM/dd");
    }
};
    public static synchronized Date fomat(String str){

        try{
            return st.get().parse(str);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

public class TestRefence {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run(){
                Date date = DateUtil.fomat("2021/09/02");
                System.out.println(date);
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                Date date = DateUtil.fomat("2021/09/02");
                System.out.println(date);
            }
        }.start();
    }
}
