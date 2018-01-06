package com.tufei.architecturedemo.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wjc on 2017/12/28.日期,时间选择器
 */
@SuppressWarnings("ResourceType,unused")
public class DateTimePicker {
    private Context mContext;


    public DateTimePicker(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 显示日期选择器
     *
     * @param pattern 日期格式化字符串
     */
    public void showDatePickDialog(final String pattern, String dateStr, final @NonNull OnPickListener onPickListener) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            final Calendar calendar = Calendar.getInstance();
            if (!TextUtils.isEmpty(dateStr)) {
                calendar.setTime(sdf.parse(dateStr));
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                    Date date = calendar.getTime();
                    long timeInMillis = calendar.getTimeInMillis();

                    onPickListener.onPick(sdf.format(date), timeInMillis / 1000);
                }
            };
            DatePickerDialog dialog = new DatePickerDialog(mContext, AlertDialog.THEME_HOLO_LIGHT, onDateSetListener, year, month, day);
            dialog.show();
        } catch (ParseException e) {
            Toast.makeText(mContext, "Date format error", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 显示时间选择器
     *
     * @param pattern    时间格式化字符串
     * @param needSecond 是否需要精确到秒,影响最后返回的long值;
     *                   true-11:37:23 false-11:37:00
     */
    public void showTimePickerDialog(final String pattern, final boolean needSecond, final @NonNull OnPickListener onPickListener) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (needSecond) {
                    calendar.set(year, month, day, hourOfDay, minute);
                } else {
                    calendar.set(year, month, day, hourOfDay, minute, 0);
                }

                Date date = calendar.getTime();
                long timeInMillis = calendar.getTimeInMillis();
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
                onPickListener.onPick(sdf.format(date), timeInMillis / 1000);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, AlertDialog.THEME_HOLO_LIGHT, onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }

    public interface OnPickListener {
        /**
         * 日期或时间选择确认时间
         *
         * @param formatStr     格式化后的日期或时间
         * @param timeInSeconds 精确到秒的长整形时间
         */
        void onPick(String formatStr, long timeInSeconds);
    }
}
