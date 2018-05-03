package com.ukar.jdk1_8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * Created by jyou on 2018/4/10.
 */
public class DateTimeApi {

    /**
     * 获取当前LocalDateTime
     * @return
     */
    public static LocalDateTime getNowTime(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    /**
     * 获取当前日期
     */
    public static LocalDate getNowDate(LocalDateTime localDateTime){
        return localDateTime.toLocalDate();
    }

    /**
     * 获取当前年份,同理可获得月份，天，小时，秒
     */
    public static int getNowYear(LocalDateTime localDateTime){
        return localDateTime.getDayOfMonth();
    }
    /**
     * 根据年月日构建LocalDate
     */
    public static LocalDate localDateOf(int year, Month month, int day){
        return LocalDate.of(year, month, day);
    }

    /**
     * 构建时分 LocalTime
     */
    public static LocalTime localTimeOf(int second, int min){
        return LocalTime.of(second, min);
    }

    /**
     * 解析时分
     * @param time:22：15 格式
     */
    public static LocalTime parseLocalTime(String time){
        return LocalTime.parse(time);
    }

    /**
     * 解析日期
     * @param time:yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime parseLocalDateTime(String time){
        return LocalDateTime.parse(time);
    }


    public static void main(String[] args) {
        LocalTime localTime = DateTimeApi.parseLocalTime("22:15");
        System.out.println(localTime);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getYear());
        System.out.println(now.getMinute());

    }
}
