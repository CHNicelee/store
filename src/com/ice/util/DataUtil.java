package com.ice.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by asd on 9/22/2017.
 */
public class DataUtil {

    public static String getTodayData(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year+"-"+month+"-"+day;
    }

    public static Timestamp getSpecifiedDay(int diff){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,diff);
        Date time=cal.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time));
        return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time));
    }

    public static Timestamp getToday(){
        Timestamp ts = Timestamp.valueOf(getTodayData()+" 00:00:00");

        return ts;
    }


    public static void main(String[] args) {
        System.out.println(getTodayData());
    }

}
