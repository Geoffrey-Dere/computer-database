package com.excilys.cdb.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public abstract class DateFormatter {

    private static DateTimeFormatter formatter;

    private static final Map<DateTimeFormatter, String> pattern = new HashMap<DateTimeFormatter, String>();

    static {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        pattern.put(formatter, "yyyy-MM-dd");
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
    public static String LocalDateToString(LocalDate date) {
        return date.format(formatter);
    }

    
    public static String getCurrentPattern() {
        return pattern.get(formatter);
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
