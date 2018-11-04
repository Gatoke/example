package pl.karoldominiak.example.application.utils;

import org.apache.commons.lang3.StringUtils;
import pl.karoldominiak.example.application.exception.ParsingDateFailedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDate parse(final String date) throws ParsingDateFailedException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (final DateTimeParseException exception) {
            throw new ParsingDateFailedException(date, DATE_FORMAT, exception);
        }
    }
}
