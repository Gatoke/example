package pl.karoldominiak.example.application.persistence;

import pl.karoldominiak.example.application.exception.ProfileReadModelNotFoundException;
import pl.karoldominiak.example.application.model.ProfileReadModel;

public interface ProfileReadModelRepository {

    void save(final ProfileReadModel profileReadModel);

    ProfileReadModel findByProfileId(final Long id) throws ProfileReadModelNotFoundException;
}
