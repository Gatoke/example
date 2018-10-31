package pl.karoldominiak.example.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileReadModel {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long phoneArea;

    private Long phoneNumber;

    private LocalDate dateOfBirth;

    private OffsetDateTime creationDate;

    public ProfileReadModel(final Long id,
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
        creationDate = OffsetDateTime.now();
    }
}
