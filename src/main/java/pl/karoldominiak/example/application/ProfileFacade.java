package pl.karoldominiak.example.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.karoldominiak.example.application.command.CreateProfileCommand;
import pl.karoldominiak.example.application.exception.GeneratingProfileIdFailedException;
import pl.karoldominiak.example.application.exception.ProfileReadModelNotFoundException;
import pl.karoldominiak.example.application.model.ProfileReadModel;
import pl.karoldominiak.example.application.persistence.ProfileReadModelRepository;
import pl.karoldominiak.example.application.utils.ProfileIdGenerator;
import pl.karoldominiak.example.domain.ProfileService;
import pl.karoldominiak.example.domain.model.Profile;

@Service
@AllArgsConstructor
public class ProfileFacade {

    private final ProfileService profileService;
    private final ProfileIdGenerator profileIdGenerator;
    private final ProfileReadModelRepository profileReadModelRepository;

    public ProfileReadModel getProfileReadModel(final Long profileId) throws ProfileReadModelNotFoundException {
        return profileReadModelRepository.findByProfileId(profileId);
    }

    public Profile createProfile(final CreateProfileCommand command) throws GeneratingProfileIdFailedException {
        final Long profileId = profileIdGenerator.generateRandomProfileId();
        return profileService.createProfile(profileId,
                                            command.getFirstName(),
                                            command.getLastName(),
                                            command.getEmail(),
                                            command.getPhoneArea(),
                                            command.getPhoneNumber(),
                                            command.getDateOfBirth());
    }
}
