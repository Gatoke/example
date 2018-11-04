package pl.karoldominiak.example.application.exception;

public class GeneratingProfileIdFailedException extends Throwable {

    public GeneratingProfileIdFailedException(final Throwable exception) {
        super(String.format("Could not generate random profile id. Exception: %s", exception.getMessage()));
    }
}
