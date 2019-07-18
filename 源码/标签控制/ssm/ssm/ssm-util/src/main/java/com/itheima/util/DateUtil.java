package com.itheima.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/********
 * author:shenkunlin
 * date:2018/8/24 9:02
 * description:深圳黑马
 * version:1.0
 ******/
public class DateUtil {

    public static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    /***
     * 2=to
     * @return
     */
    public static  String date2Str1(Date date){
        return simpleDateFormat1.format(date);
    }

}
