package com.kairong.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.util
 * @date:2020/6/8
 */
public class TimeUtil {

    public static String getTimeWithYearAndMonth(LocalDateTime time) {
        return getTimeByPatter(time, "yyyy-MM");
    }



    public static String getTimeWithSecond(LocalDateTime time) {
        return getTimeByPatter(time, "yyyy-MM-dd HH:mm:ss");
    }


    public static String getTimeByPatter(LocalDateTime time, String pattern) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(pattern);
        return dtf2.format(time);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
