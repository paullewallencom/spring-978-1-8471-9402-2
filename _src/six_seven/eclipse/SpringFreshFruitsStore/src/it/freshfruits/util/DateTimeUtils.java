package it.freshfruits.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;

public class DateTimeUtils {

    public static String getDateNow() {
        return new DateTime().toString();
    }

    public static DateTime getDateNowToNextMonths(int numberMonths) {
        DateTime now = new DateTime();
        ReadablePeriod noUpdatePeriod = new Period().withMonths(numberMonths);
        return now.plus(noUpdatePeriod);
    }

    public static DateTime getDateNowToNextDays(int numberDays) {
        DateTime now = new DateTime();
        ReadablePeriod noUpdatePeriod = new Period().withDays(numberDays);
        return now.plus(noUpdatePeriod);
    }

    public static DateTime getDaysDateBeforeNow(int numberDays) {
        DateTime now = new DateTime();
        ReadablePeriod noUpdatePeriod = new Period().withDays(numberDays);
        return now.minus(noUpdatePeriod);
    }

    public static DateTime getDateNowToNextYears(int numberYears) {
        DateTime now = new DateTime();
        ReadablePeriod noUpdatePeriod = new Period().withYears(numberYears);
        return now.plus(noUpdatePeriod);
    }

    public static Date getDateFromString(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        Date date = null;
        try {
            date = new Date(sdf.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
