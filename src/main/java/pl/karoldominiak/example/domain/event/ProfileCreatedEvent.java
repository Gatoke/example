package pl.karoldominiak.example.domain.event;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProfileCreatedEvent {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Long phoneArea;
    private final Long phoneNumber;
    private final LocalDate dateOfBirth;

    public ProfileCreatedEvent(final Long id,
                               final String firstName,
                               final String lastName,
                               final String email,
                               final Long phoneArea,
                               final Long phoneNumber,
                               final LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneArea = phoneArea;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
