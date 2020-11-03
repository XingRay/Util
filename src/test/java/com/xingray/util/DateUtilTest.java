package com.xingray.util;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateUtilTest {

    @Test
    public void test01() {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalDate date = LocalDate.of(2020, 11, 3);
        System.out.println(date);

        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfYear());
        System.out.println(date.isLeapYear());
        System.out.println(date.lengthOfMonth());
        System.out.println(date.lengthOfYear());

        Month month = date.getMonth();
        System.out.println(month);
        System.out.println(month.getValue());
        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.CHINA));

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(dayOfWeek.getValue());
        System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.CHINA));
    }

    @Test
    public void test02() {
        LocalTime time = LocalTime.of(16, 18, 20);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        System.out.println(time.getNano());
    }

    @Test
    public void test03() {
        LocalDate date = LocalDate.parse("2020/11/03", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(date);

        LocalTime time = LocalTime.parse("16:18:20");
        System.out.println(time);

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println(dateTime);

        LocalDateTime localDateTime = date.atTime(time);
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = time.atDate(date);
        System.out.println(localDateTime1);

        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);
    }

    @Test
    public void test04() {
        Instant instant = Instant.now();
        long seconds = instant.getEpochSecond();
        System.out.println(seconds);

        Instant instant1 = Instant.ofEpochMilli(1604393002 * 1000L);
        System.out.println(instant1);
    }

    @Test
    public void test05() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 11, 3, 12, 0, 0);
        LocalDateTime dateTime1 = LocalDateTime.of(2020, 11, 3, 15, 0, 0);
        Duration between = Duration.between(dateTime1, dateTime);
        System.out.println(between.toMinutes());
    }

    @Test
    public void test06() {
        Period period = Period.between(LocalDate.of(2020, 11, 3), LocalDate.of(2020, 11, 5));
        System.out.println(period.getDays());
    }


    @Test
    public void test07() {
        LocalDate localDate = LocalDate.of(2020, 11, 3);
        LocalDate localDate1 = localDate.withYear(2021).withMonth(12);
        System.out.println(localDate1.minusDays(5));
    }

    @Test
    public void test08() {
        LocalDate localDate = LocalDate.of(2020, 11, 3);
        LocalDate date = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);

        LocalDate localDate1 = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(localDate1);

        LocalDate localDate2 = localDate.with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.SUNDAY));
        System.out.println(localDate2);
    }

    @Test
    public void test09() {
        LocalDate localDate = LocalDate.parse("20201103", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);

        LocalDate localDate1 = LocalDate.parse("2020-11-03", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate1);
    }

    @Test
    public void test10() {
        ZoneId zoneId = ZoneId.of("+08:00");
        System.out.println(zoneId);

        ZoneId zoneId1 = ZoneId.of("-04:00");
        System.out.println(zoneId1);

        LocalDate localDate = LocalDate.parse("2020-11-03");
        LocalDateTime localDateTime = localDate.atTime(17, 6, 0);
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.toEpochMilli());

        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, zoneId1);
        System.out.println(localDateTime1);
    }

    @Test
    public void test11() {
        long time = 1604462399L;
        Instant instant = Instant.ofEpochSecond(1604462399L);

        ZoneId zoneId1 = ZoneId.of("-04:00");
        System.out.println(zoneId1);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId1);
        System.out.println(localDateTime);

        String timeString = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(timeString);
    }

    @Test
    public void test12() {
        LocalDate localDate = LocalDate.of(2020, 2, 29);
        System.out.println(localDate);
    }

    @Test
    public void test13() {
        ZoneId ZONE_ID_BEIJING = ZoneId.of("+08:00");
        ZoneId ZONE_ID_US = ZoneId.of("-04:00");

        System.out.println(DateUtil.startMillsOfSameDay(DateUtil.nowMills(), ZONE_ID_BEIJING));
        System.out.println(DateUtil.endMillsOfSameDay(DateUtil.nowMills(), ZONE_ID_BEIJING));
        System.out.println(DateUtil.startSecondsOfSameDay(DateUtil.nowSecond(), ZONE_ID_BEIJING));
        System.out.println(DateUtil.endSecondsOfSameDay(DateUtil.nowSecond(), ZONE_ID_BEIJING));


        System.out.println(DateUtil.startMillsOfSameDay(DateUtil.nowMills(), ZONE_ID_US));
        System.out.println(DateUtil.endMillsOfSameDay(DateUtil.nowMills(), ZONE_ID_US));
        System.out.println(DateUtil.startSecondsOfSameDay(DateUtil.nowSecond(), ZONE_ID_US));
        System.out.println(DateUtil.endSecondsOfSameDay(DateUtil.nowSecond(), ZONE_ID_US));
    }
}
