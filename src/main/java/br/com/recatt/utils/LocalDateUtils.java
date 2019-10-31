package br.com.recatt.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate stringToLocalDate(final String stringDate) {
        return LocalDate.parse(stringDate, FORMATTER);
    }

    public static String localDateToString(final LocalDate date) {
        return date.format(FORMATTER);
    }
}
