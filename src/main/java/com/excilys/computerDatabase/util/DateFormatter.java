package com.excilys.computerDatabase.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateFormatter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

    /**
     * @param date The date to be converted
     * @return the LocalDate
     */
    public static LocalDate stringtoLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
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
