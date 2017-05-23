package com.excilys.cdb.core.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public abstract class DateFormatter {

    /**
     */
    private static final Map<DateTimeFormatter, String> PATTERN = new HashMap<DateTimeFormatter, String>();

    private static DateTimeFormatter formatter;

    static {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PATTERN.put(formatter, "yyyy-MM-dd");
    }

    /**
     * @param date The date to be converted
     * @return the LocalDate
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static LocalDate stringtoLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    /**
     * @param date The date to be transformed
     * @return The date in string
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static String localDateToString(LocalDate date) {
        return date.format(formatter);
    }

    /**
     * @return current pattern
     */
    public static String getCurrentPattern() {
        return PATTERN.get(formatter);
    }

    /**
     * @param date The date to be converted
     * @return The date in format sql
     * @throws ParseException the parse exception
     */
    public static Date sqlDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        return new Date(parsed.getTime());
    }
}
