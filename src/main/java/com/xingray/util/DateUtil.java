package com.xingray.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static final String DEFAULT_DATE_PATTERN = "yyyy/MM/dd";
    public static final String DEFAULT_SEPARATOR = "/";
    public static final int DAY_IN_SECONDS = 24 * 3600;
    public static final int DAY_IN_MILLS = DAY_IN_SECONDS * 1000;

    public static boolean isValidDate(int year, int month, int day) {
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

    public static boolean isValidDateInterval(int startYear,
                                              int startMonth,
                                              int startDay,
                                              int endYear,
                                              int endMonth,
                                              int endDay) {
        if (!isValidDate(startYear, startMonth, startDay) || !isValidDate(endYear, endMonth, endDay)) {
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

    public static int[] millsToYmd(long mills) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(mills);
        return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)};
    }

    public static int[] secondsToYmd(long seconds) {
        return millsToYmd(seconds * 1000);
    }

    public static long ymdToSeconds(int year,
                                    int month,
                                    int day) {
        return ymdToMills(year, month, day) / 1000;
    }

    public static long ymdToMills(int year,
                                  int month,
                                  int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.set(year, month - 1, day, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static long ymdStringToMillsValue(String s) {
        return ymdStringToMillsValue(s, DEFAULT_SEPARATOR);
    }

    public static long ymdStringToMillsValue(String s, String separator) {
        int[] ints = StringUtil.toInts(s, separator);
        return ymdToMills(ints[0], ints[1], ints[2]);
    }

    public static long ymdStringToSecondsValue(String text) {
        return ymdStringToSecondsValue(text, DEFAULT_SEPARATOR);
    }

    public static long ymdStringToSecondsValue(String text, String separator) {
        return ymdStringToMillsValue(text, separator) / 1000;
    }

    public static Long ymdStringToMills(String s) {
        return ymdStringToMills(s, DEFAULT_SEPARATOR);
    }

    public static Long ymdStringToMills(String s, String sep) {
        Integer[] integers = StringUtil.toIntegers(s, sep);
        if (integers == null || integers.length < 3) {
            return null;
        }
        for (Integer i : integers) {
            if (i == null) {
                return null;
            }
        }

        return ymdToMills(integers[0], integers[1], integers[2]);
    }

    public static Long ymdStringToSeconds(String s) {
        return ymdStringToSeconds(s, DEFAULT_SEPARATOR);
    }

    public static Long ymdStringToSeconds(String s, String sep) {
        Long mills = ymdStringToMills(s, sep);
        if (mills == null) {
            return null;
        }
        return mills / 1000;
    }

    public static String millsToYmdString(Long mills, String separator) {
        if (mills == null) {
            return null;
        }

        int[] ints = millsToYmd(mills);
        return ints[0] + separator + ints[1] + separator + ints[2];
    }

    public static String millsToYmdString(Long mills) {
        return millsToYmdString(mills, DEFAULT_SEPARATOR);
    }

    public static String secondsToYmdString(Long seconds, String separator) {
        if (seconds == null) {
            return null;
        }
        return millsToYmdString(seconds * 1000, separator);
    }

    public static String secondsToYmdString(Long seconds) {
        return secondsToDateString(seconds, DEFAULT_SEPARATOR);
    }


    public static String millsToDateString(Long mills, String format) {
        if (mills == null) {
            return null;
        }
        return toDateString(millsValueToDate(mills), format);
    }

    public static String millsToDateString(Long mills) {
        return millsToDateString(mills, DEFAULT_DATE_PATTERN);
    }

    public static String secondsToDateString(Long seconds, String format) {
        if (seconds == null) {
            return null;
        }
        return millsToDateString(seconds * 1000, format);
    }

    public static String secondsToDateString(Long seconds) {
        return secondsToDateString(seconds, DEFAULT_DATE_PATTERN);
    }

    public static String millsValueToDateString(long mills, String format) {
        return toDateString(millsValueToDate(mills), format);
    }

    public static Date millsToDate(Long mills) {
        if (mills == null) {
            return null;
        }
        return millsValueToDate(mills);
    }

    public static Date millsValueToDate(long mills) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(mills);
        return calendar.getTime();
    }

    public static Date secondsToDate(Long seconds) {
        if (seconds == null) {
            return null;
        }
        return secondsValueToDate(seconds);
    }

    public static Date secondsValueToDate(long seconds) {
        return millsValueToDate(seconds * 1000);
    }

    public static String millsValueToDateString(long mills) {
        return millsValueToDateString(mills, DEFAULT_DATE_PATTERN);
    }

    public static String secondsValueToDateString(long seconds, String format) {
        return millsValueToDateString(seconds * 1000, format);
    }

    public static String secondsValueToDateString(long seconds) {
        return secondsValueToDateString(seconds, DEFAULT_DATE_PATTERN);
    }

    public static String toDateString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String toDateString(Date date) {
        return toDateString(date, DEFAULT_DATE_PATTERN);
    }


    public static Date getToday() {
        return getTodayCalendar().getTime();
    }

    private static Calendar getTodayCalendar() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar;
    }

    public static long getTodayMills() {
        return getTodayCalendar().getTimeInMillis();
    }

    public static long getTodaySeconds() {
        return getTodayMills() / 1000;
    }

    public static String todayToDateString() {
        return todayToDateString(DEFAULT_DATE_PATTERN);
    }

    public static String todayToDateString(String format) {
        return toDateString(getToday(), format);
    }

    public static long nowMills() {
        return System.currentTimeMillis();
    }

    public static long nowSecond() {
        return nowMills() / 1000;
    }
}
