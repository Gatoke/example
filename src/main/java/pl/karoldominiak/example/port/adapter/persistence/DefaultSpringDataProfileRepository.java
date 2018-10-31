package pl.karoldominiak.example.port.adapter.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.karoldominiak.example.domain.model.Profile;
import pl.karoldominiak.example.domain.persistence.ProfileNotFoundException;
import pl.karoldominiak.example.domain.persistence.ProfileRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultSpringDataProfileRepository implements ProfileRepository {

    private final SpringDataProfileRepository repository;

    @Override
    public void save(final Profile profile) {
        repository.save(profile);
    }

    @Override
    public Profile findByProfileId(final Long profileId) throws ProfileNotFoundException {
        final Optional<Profile> profileOptional = repository.findById(profileId);
        if (profileOptional.isPresent()) {
            return profileOptional.get();
        }
        throw new ProfileNotFoundException(profileId);
    }

    @Override
    public Optional<Profile> findOptionalByProfileId(final Long profileId) {
        return repository.findById(profileId);
    }
}
