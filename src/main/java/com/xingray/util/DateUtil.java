package com.xingray.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    public static boolean checkDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            return false;
        }
        int lastDay = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            default -> {
                if (isLeapYear(year)) {
                    yield 29;
                } else {
                    yield 28;
                }
            }
        };
        return day >= 1 && day <= lastDay;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean checkDateInterval(int startYear,
                                            int startMonth,
                                            int startDay,
                                            int endYear,
                                            int endMonth,
                                            int endDay) {
        if (!checkDate(startYear, startMonth, startDay) || !checkDate(endYear, endMonth, endDay)) {
            return false;
        }
        if (startYear < endYear) {
            return true;
        } else if (startYear > endYear) {
            return false;
        }

        if (startMonth < endMonth) {
            return true;
        } else if (startMonth > endMonth) {
            return false;
        }

        return startDay <= endDay;
    }

    public static long dateToSeconds(int year,
                                     int month,
                                     int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTimeInMillis() / 1000;
    }

    public static int[] secondsToYMD(long seconds) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(seconds * 1000);
        return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)};
    }

    public static long parseDateToTimeInMills(String text) {
        return parseDateToTimeInSeconds(text, "/");
    }

    public static long parseDateToTimeInMills(String text, String sep) {
        int[] ints = StringUtil.toInts(text, sep);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(ints[0], ints[1] - 1, ints[2], 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static long parseDateToTimeInSeconds(String text) {
        return parseDateToTimeInSeconds(text, "/");
    }

    public static long parseDateToTimeInSeconds(String text, String sep) {
        return parseDateToTimeInMills(text, sep) / 1000;
    }

    public static Long parseDateToMills(String s) {
        return parseDateToMills(s, "/");
    }

    public static Long parseDateToMills(String s, String sep) {
        Integer[] integers = StringUtil.toIntegers(s, sep);
        if (integers == null || integers.length < 3) {
            return null;
        }
        for (Integer i : integers) {
            if (i == null) {
                return null;
            }
        }

        Calendar calendar = GregorianCalendar.getInstance();
        //noinspection MagicConstant
        calendar.set(integers[0], integers[1] - 1, integers[2], 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static Long ParseDateToSeconds(String s) {
        return ParseDateToSeconds(s, "/");
    }

    public static Long ParseDateToSeconds(String s, String sep) {
        Long mills = parseDateToMills(s, sep);
        if (mills == null) {
            return null;
        }
        return mills / 1000;
    }


    public static String secondsToDateString(Long seconds) {
        if (seconds == null) {
            return null;
        }
        return millsToDateString(seconds * 1000);
    }

    public static String secondsToDateString(long seconds) {
        return millsToDateString(seconds * 1000);
    }

    public static String secondsToDateString(long seconds, String format) {
        return millsToDateString(seconds * 1000, format);
    }

    private static String millsToDateString(long mills, String format) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(mills);
        return toDateString(calendar.getTime(), format);
    }

    public static String millsToDateString(long mills) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(mills);
        return toDateString(calendar.getTime());
    }

    public static String toDateString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String toDateString(Date date) {
        return DEFAULT_FORMAT.format(date);
    }

    public static Date getToday() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }

    public static long getTodayInMills() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static long getTodayInSeconds() {
        return getTodayInMills() / 1000;
    }

    public static String todayToDateString() {
        return toDateString(getToday());
    }

    public static String todayToDateString(String format) {
        return toDateString(getToday(), format);
    }
}
