package pl.karoldominiak.example.application.exception;

public class ParsingDateFailedException extends Throwable {

    public ParsingDateFailedException(final String date, final String expectedFormat, final Throwable exception) {
        super(String.format("Can not parse date of format: %s. Be sure it is of format: %s. Exception: %s",
                            date,
                            expectedFormat,
                            exception));
    }
}
