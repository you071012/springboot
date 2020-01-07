package com.ukar.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author jia.you
 * @date 2019/12/30
 */
public class UkarDateUtils {
    private static final String DEFAULT_FMART = "yyyyMMdd";

    public static long diffDays(Date start, Date end){
        return DateUtil.between(start, end, DateUnit.DAY, false);
    }

    public static void main(String[] args) {

        DateTime start = DateUtil.parse("20200101", DEFAULT_FMART);
        DateTime end = DateUtil.parse("20191231", DEFAULT_FMART);

        System.out.println(diffDays(start, end));

    }
}
