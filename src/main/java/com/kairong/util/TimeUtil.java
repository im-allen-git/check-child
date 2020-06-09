package com.kairong.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
