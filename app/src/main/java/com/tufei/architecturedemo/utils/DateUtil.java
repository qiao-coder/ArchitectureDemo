package com.tufei.architecturedemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;


import com.tufei.architecturedemo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dugang on 2017/12/28. 日期时间工具类
 */
@SuppressWarnings("unused")
public class DateUtil {

    /**
     * 按照格式获取当前的日期
     */
    public static String currentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date().getTime());
    }

    /**
     * 格式化Long型日期,secondLevel为true时会转换为毫秒级再格式化
     */
    public static String formatDate(String pattern, long date, boolean secondLevel) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(date * (secondLevel ? 1000L : 1L)));
    }

    /**
     * 获取今天星期几
     */
    public static String getDayOfWeek(Context context) {
        String[] weekDays = context.getResources().getStringArray(R.array.week);
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w < 0 ? 0 : w];
    }
}
