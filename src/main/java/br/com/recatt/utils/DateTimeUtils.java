package br.com.recatt.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER = ofPattern("dd/MM/yyyy");

    public static LocalDate stringToLocalDate(final String stringDate) {
        return LocalDate.parse(stringDate, FORMATTER);
    }

    public static String localDateToString(final LocalDate date) {
        return date.format(FORMATTER);
    }

    public static LocalTime stringToLocalTime(final String stringTime) {
        return LocalTime.parse(stringTime, ofPattern("H:mm"));
    }

    public static Integer getMinutesDuration(final LocalTime time) {

        int h = time.getHour();
        int m = time.getMinute();
        return (h * 60) + m;
    }

    public static boolean isBetween(final LocalTime localTime, final LocalTime first, final LocalTime second) {

        return localTime.compareTo(first) >= 0 && localTime.compareTo(second) <= 0;
    }
}
