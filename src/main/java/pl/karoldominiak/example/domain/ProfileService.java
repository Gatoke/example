package pl.karoldominiak.example.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.karoldominiak.example.domain.model.Profile;
import pl.karoldominiak.example.domain.persistence.ProfileRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile createProfile(final Long profileId,
                                 final String firstName,
                                 final String lastName,
                                 final String email,
                                 final Long phoneArea,
                                 final Long phoneNumber,
                                 final LocalDate dateOfBirth) {
        final Profile profile = new Profile(profileId, firstName, lastName, email, phoneArea, phoneNumber, dateOfBirth);
        profileRepository.save(profile);
        return profile;
    }
}
