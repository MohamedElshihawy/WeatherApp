package com.example.weatherv2.WeatherUtilities;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TransformUnixTime {

    public static String getDayName(long unixTime)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
        Date dateFormat = new java.util.Date(unixTime * 1000);

        return sdf.format(dateFormat);
    }



    public static String getHourOfDay(long unixTime)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(unixTime * 1000);
        calendar.get(Calendar.HOUR);
        return calendar.get(Calendar.HOUR_OF_DAY)+"";
    }

    public static String getTodayDate(String unixTimeString)
    {
        long unixTime = Long.parseLong(unixTimeString);
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("MM-dd");
        return jdf.format(date);
    }
}
