package com.excilys.cdb.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DateFormatter {

    /**
     */
    private static final Map<Locale, DateTimeFormatter> PATTERN = new HashMap<Locale, DateTimeFormatter>();

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatter.class);

    private static DateTimeFormatter formatter;

    static {
        formatter = DEFAULT_FORMATTER;
        PATTERN.put(Locale.FRANCE, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PATTERN.put(Locale.ENGLISH, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
     * @param language language
     */
    public static void setFormatter(String language) {

        if (Locale.FRENCH.equals(new Locale(language))) {
            formatter = PATTERN.get(Locale.FRANCE);
        } else {
            formatter = DEFAULT_FORMATTER;
        }
    }
}
