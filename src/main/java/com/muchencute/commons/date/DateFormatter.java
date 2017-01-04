package com.muchencute.commons.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("ALL")
public class DateFormatter {

    private static DateFormatter ourInstance = new DateFormatter();

    private static SimpleDateFormat mSimpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private DateFormatter() {
    }

    public static String getSimpleDateTimeFormat() {
        return getSimpleDateTimeFormat(new Date());
    }

    public static String getSimpleDateTimeFormat(Date date) {
        return mSimpleDateTimeFormat.format(date);
    }

    public static String getCustomizedDateTimeFormat(String fmt) {
        return getCustomizedDateTimeFormat(new Date(), fmt);
    }

    public static String getCustomizedDateTimeFormat(Date date, String fmt) {
        return new SimpleDateFormat(fmt).format(date);
    }

    public static Date dateAfterSeconds(Date date, int seconds) {
        return new Date(date.getTime() + seconds * 1000);
    }

    public static String getUTCDateTime() {
        return getUTCDateTime(new Date());
    }

    public static String getUTCDateTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }
}
