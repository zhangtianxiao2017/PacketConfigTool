package com.test.config.dev.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static String getNowDateString(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhh_mm_ss");
        String format = sd.format(new Date());
        return format;
    }

}
