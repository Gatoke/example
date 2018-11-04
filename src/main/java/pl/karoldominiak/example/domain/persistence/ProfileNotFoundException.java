package pl.karoldominiak.example.domain.persistence;

public class ProfileNotFoundException extends Throwable {

    public ProfileNotFoundException(final Long profileId) {
        super(String.format("Profile with id %s not found.", profileId.toString()));
    }
}
