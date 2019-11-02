package br.com.recatt.utils;

public final class ParseUtils {

    public static Double round(final Double value, final Integer places) {

        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static Double getPercentage(final long obtained, final Integer total) {

        return (obtained * 100) / (total.doubleValue());
    }
}
