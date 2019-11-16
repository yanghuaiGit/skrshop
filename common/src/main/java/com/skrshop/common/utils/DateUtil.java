package com.skrshop.common.utils;

import java.time.*;

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

    }

    /**
     * 时间比较类
     */
    public static class DateCompare {

    }

}
