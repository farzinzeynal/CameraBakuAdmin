package com.farzinzeynal.camerabakuadmin.ui.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils
{
    public static String getFullDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return df.format(date);
    }

    public static String getFormattedDate(Date date) {
        CharSequence ago = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = date.getTime();
        ago = android.text.format.DateUtils.getRelativeTimeSpanString(time);
        return ago.toString();
    }
}
