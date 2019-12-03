package com.skrshop.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {


    /**
     * 时间获取类
     */
    public static class DateGet {
        /**
         * @return 2019-11-16T14:22:57.318
         */
        public static LocalDateTime currentDateTime() {
            return LocalDateTime.now();
        }

        public static ZonedDateTime currentDateTimeWithZone(ZoneId zoneId) {
            return currentDateTime().atZone(zoneId);
        }

        public static LocalDate currentDate() {
            return LocalDate.now();
        }

        public static LocalTime currentTime() {
            return LocalTime.now();
        }

        public static int currentYear() {
            return currentDate().getYear();
        }

        public static int currentMonth() {
            return currentDate().getMonthValue();
        }

        public static int currentDayOfWeek() {
            return currentDate().getDayOfWeek().getValue();
        }

        public static int currentDayOfMonth() {
            return currentDate().getDayOfMonth();
        }

        public static int currentDayOfYear() {
            return currentDate().getDayOfYear();
        }

        public static int currentHour() {
            return currentTime().getHour();
        }

        public static int currentMinute() {
            return currentTime().getMinute();
        }

        public static int currentSecond() {
            return currentTime().getSecond();
        }

        public static int currentYearWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getYear();
        }

        public static int currentMonthWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getMonthValue();
        }

        public static int currentDayOfYearWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getDayOfYear();
        }

        public static int currentDayOfMonthWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getDayOfMonth();
        }

        public static int currentDayOfWeekWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getDayOfWeek().getValue();
        }

        public static int currentHourWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getHour();
        }

        public static int currentSecondWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getSecond();
        }

        public static int currentMinuteWithZone(ZoneId zoneId) {
            return currentDateTimeWithZone(zoneId).getMinute();
        }

    }

    /**
     * 时间创建类
     */
    public static class DateCreate {
        public static LocalDateTime ofTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
            return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        }

        public static LocalDate ofDate(int year, int month, int dayOfMonth) {
            return LocalDate.of(year, month, dayOfMonth);
        }

        public static LocalTime oftime(int hour, int minute, int second) {
            return LocalTime.of(hour, minute, second);
        }

        //time 格式 2019-10-01T22:22:22.222"
        public static LocalDateTime parseStringToLocalDateTime(String time) {
            return LocalDateTime.parse(time);
        }

        //格式 20190101
        public static LocalDate parseStringToLocalDate(String time) {
            return LocalDate.parse(time, DateTimeFormatter.BASIC_ISO_DATE);
        }

        public static LocalDateTime dateConventLocalDateTime(Date date) {
            ZoneId zoneId = ZoneId.systemDefault();
            return dateConventLocalDateTime(date, zoneId);

        }

        public static LocalDateTime dateConventLocalDateTime(Date date, ZoneId zoneId) {
            return LocalDateTime.ofInstant(date.toInstant(), zoneId);

        }

        public static Date localDateTimeConventDate(LocalDateTime localDateTime, ZoneId zoneId) {
            return Date.from(localDateTime.atZone(zoneId).toInstant());
        }

        public static Long currentTime(ZoneOffset zoneOffset) {
            return LocalDateTime.now().toInstant(zoneOffset).toEpochMilli();
        }

        //转为时间戳
        public static Long epochMilli() {
            return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }

        //时间戳转为time
        public static LocalDateTime epochMilliTime(Long epochMilli) {
            return epochMilliTime(epochMilli, ZoneId.systemDefault());
        }

        public static LocalDateTime epochMilliTime(Long epochMilli, ZoneId zoneId) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), zoneId);
        }

        //formatter "YYYY-MM-dd hh:mm:ss"
        public static String timeToString(LocalDateTime localDateTime, String formatter) {
            return localDateTime.format(DateTimeFormatter.ofPattern(formatter));
        }

        public static String timeToStringSencond(LocalDateTime localDateTime) {
            return timeToString(localDateTime, "YYYY-MM-dd hh:mm:ss");
        }

        public static String timeToStringDay(LocalDateTime localDateTime) {
            return timeToString(localDateTime, "YYYY-MM-dd");
        }
    }

    /**
     * 时间比较类
     */
    public static class DateCompare {
        // 时间差

        public static long untilDay(LocalDateTime dateTime, LocalDateTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.DAYS);
        }

        public static long untilMonth(LocalDateTime dateTime, LocalDateTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.MONTHS);
        }

        public static long untilHours(LocalDateTime dateTime, LocalDateTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.HOURS);
        }

        public static long untilMinutes(LocalDateTime dateTime, LocalDateTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.MINUTES);
        }

        public static long untilDay(LocalDate dateTime, LocalDate dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.DAYS);
        }

        public static long untilMonth(LocalDate dateTime, LocalDate dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.MONTHS);
        }

        public long untilHours(LocalDate dateTime, LocalDate dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.HOURS);
        }

        public static long untilMinutes(LocalDate dateTime, LocalDate dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.MINUTES);
        }

        public static long untilMinutes(LocalTime dateTime, LocalTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.MINUTES);
        }

        public static long untilDay(LocalTime dateTime, LocalTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.DAYS);
        }

        public static long untilMonth(LocalTime dateTime, LocalTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.MONTHS);
        }

        public static long untilHours(LocalTime dateTime, LocalTime dateTime1) {
            return dateTime.until(dateTime1, ChronoUnit.HOURS);
        }
    }

}
