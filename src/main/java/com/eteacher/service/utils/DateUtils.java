package com.eteacher.service.utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static java.util.Objects.isNull;

public class DateUtils {

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";

    public static String format(Date date, String format) {
        return isNull(date) ? null : new SimpleDateFormat(format).format(date);
    }

    public static Date asDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static Date asDate(String date) {
        return java.sql.Date.valueOf(date);
    }

    public static Date asDateTime(String stringToDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        //String dateInString = "22-01-2015 10:15:55 AM";
        Date date = formatter.parse(stringToDate);
        String formattedDateString = formatter.format(date);
        //System.out.println(LocalDateTime.parse(date));
        return date;
    }

}
