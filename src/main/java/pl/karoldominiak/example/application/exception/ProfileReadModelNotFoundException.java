package pl.karoldominiak.example.application.exception;

public class ProfileReadModelNotFoundException extends Throwable {

    public ProfileReadModelNotFoundException(final Long profileId) {
        super(String.format("Profile read model with id %s not found.", profileId.toString()));
    }
}
