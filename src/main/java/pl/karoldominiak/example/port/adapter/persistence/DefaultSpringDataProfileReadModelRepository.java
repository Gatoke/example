package pl.karoldominiak.example.port.adapter.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.karoldominiak.example.application.exception.ProfileReadModelNotFoundException;
import pl.karoldominiak.example.application.model.ProfileReadModel;
import pl.karoldominiak.example.application.persistence.ProfileReadModelRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultSpringDataProfileReadModelRepository implements ProfileReadModelRepository {

    private final SpringDataProfileReadModelRepository repository;

    @Override
    public void save(final ProfileReadModel profileReadModel) {
        repository.save(profileReadModel);
    }

    @Override
    public ProfileReadModel findByProfileId(final Long profileId) throws ProfileReadModelNotFoundException {
        final Optional<ProfileReadModel> profileOptional = repository.findById(profileId);
        if (profileOptional.isPresent()) {
            return profileOptional.get();
        }
        throw new ProfileReadModelNotFoundException(profileId);
    }
}
