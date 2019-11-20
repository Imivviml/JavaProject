/**
 * FileName: DateConverter
 * Author:   #include
 * Date:     2019/10/9 15:34
 * Description:
 */
package com.itheima.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;


/**
 * 把页面提交的日期字符串转换成日期类
 */
public class DateConverter implements Converter<String, Date> {


    /**
     * String source 传入进来的参数
     * @param source
     * @return
     */
    @Override
    public Date convert(String source) {
        if (source == null){
            throw new RuntimeException("请传入数据!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //把日期类型转换成日期
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("数据类型异常!");
        }
    }
}