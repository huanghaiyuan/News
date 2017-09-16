package cn.qizhidao.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */

public class DateUtils {
    public static String longToDate(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt = new Date(l*1000);
        String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }
}
