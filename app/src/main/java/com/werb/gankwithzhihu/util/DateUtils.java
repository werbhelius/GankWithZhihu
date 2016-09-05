package com.werb.gankwithzhihu.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Werb on 2016/7/7.
 * 日期工具类
 */
public class DateUtils {

    public static boolean isSameDate(Date date1, Date date2) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(date2);

        return cal.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR);
    }

}
