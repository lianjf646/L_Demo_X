package com.phph.x_support_lib.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by v on 2019/12/12.
 */
public class DateHelper {


    private static class Inner {
        static DateHelper dateHelper = new DateHelper();
    }

    public static DateHelper getInstance() {
        return Inner.dateHelper;
    }

    private DateHelper() {

    }

    public String getWeekOfDate(Date date) {
        /**
         *
         * 功能描述: 根据日期获取周几
         *
         * @auther: lkj
         * @date: 2018/4/3 下午1:46
         * @param: [date]
         * @return: java.lang.String
         *
         */
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    public String getDateStr(Date date, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

}
