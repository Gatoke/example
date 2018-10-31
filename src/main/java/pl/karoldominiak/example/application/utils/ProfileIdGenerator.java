package pl.karoldominiak.example.application.utils;

import lombok.AllArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;
import pl.karoldominiak.example.application.exception.GeneratingProfileIdFailedException;
import pl.karoldominiak.example.domain.model.Profile;
import pl.karoldominiak.example.domain.persistence.ProfileRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileIdGenerator {

    private final ProfileRepository profileRepository;

    public Long generateRandomProfileId() throws GeneratingProfileIdFailedException {
        try {
            final long randomLong = new RandomDataGenerator().nextLong(999999, 9999999);
            final Optional<Profile> profileOptional = profileRepository.findOptionalByProfileId(randomLong);
            if (profileOptional.isPresent()) {
                return generateRandomProfileId();
            }
            return randomLong;
        } catch (final StackOverflowError exception) {
            throw new GeneratingProfileIdFailedException(exception);
        }
    }
}
