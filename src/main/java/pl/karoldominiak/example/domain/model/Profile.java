package pl.karoldominiak.example.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;
import pl.karoldominiak.example.domain.event.ProfileCreatedEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends AbstractAggregateRoot {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneArea;
    private Long phoneNumber;
    private LocalDate dateOfBirth;

    public Profile(final Long id,
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
        registerEvent(new ProfileCreatedEvent(id, firstName, lastName, email, phoneArea, phoneNumber, dateOfBirth));
    }
}
