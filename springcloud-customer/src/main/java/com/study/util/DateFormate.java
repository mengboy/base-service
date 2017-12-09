package com.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {

    static Logger logger = LoggerFactory.getLogger(DateFormate.class);

    /**
     * 字符串转date
     * @param string
     * @return
     */
    public static Date string2Date(String string){
        SimpleDateFormat simpleDateFormat = null;
        ParsePosition parsePosition = null;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parsePosition = new ParsePosition(0);
        }catch (Exception e){
            logger.error("字符串转时间失败", e);
        }
        return simpleDateFormat.parse(string, parsePosition);
    }

    /**
     * date 转 字符串
     * @param date
     * @return
     */
    public static String date2String(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
