package pl.karoldominiak.example.domain.persistence;

import pl.karoldominiak.example.domain.model.Profile;

import java.util.Optional;

public interface ProfileRepository {

    void save(final Profile profile);

    Profile findByProfileId(final Long profileId) throws ProfileNotFoundException;

    Optional<Profile> findOptionalByProfileId(final Long profileId);
}
