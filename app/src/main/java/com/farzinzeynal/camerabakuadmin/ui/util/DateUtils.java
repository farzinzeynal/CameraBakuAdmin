package com.farzinzeynal.camerabakuadmin.ui.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils
{
    public static String getFullDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
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


/*    String imageString = adverModel.getAdver_image();
    byte[] userImagebyte = Base64.decode(imageString, Base64.DEFAULT);
    Bitmap bitmap = BitmapFactory.decodeByteArray(userImagebyte,0,userImagebyte.length);
        imageView_adver_list.setImageBitmap(bitmap);*/
}
