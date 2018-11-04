package pl.karoldominiak.example.application.handler;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.karoldominiak.example.application.model.ProfileReadModel;
import pl.karoldominiak.example.application.persistence.ProfileReadModelRepository;
import pl.karoldominiak.example.domain.event.ProfileCreatedEvent;

@Component
@AllArgsConstructor
public class CreateProfileRMOnProfileCreatedEvent {

    private final ProfileReadModelRepository repository;

    @EventListener(ProfileCreatedEvent.class)
    public void updateProfileReadModel(final ProfileCreatedEvent event) {
        final ProfileReadModel profileReadModel = new ProfileReadModel(event.getId(),
                                                                       event.getFirstName(),
                                                                       event.getLastName(),
                                                                       event.getEmail(),
                                                                       event.getPhoneArea(),
                                                                       event.getPhoneNumber(),
                                                                       event.getDateOfBirth());
        repository.save(profileReadModel);
    }
}
