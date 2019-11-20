/**
 * FileName: DateUtils
 * Author:   #include
 * Date:     2019/10/9 14:15
 * Description:
 */
package com.itheima.ssm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 完成日期格式的转换
 */
public class DateUtils {

    //日期格式转换成字符串
    public static String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;

    }

    //字符串转成成日期格式
    public static Date string2Date(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }

}
