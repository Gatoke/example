package pl.karoldominiak.example.application.command;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.karoldominiak.example.application.exception.ParsingDateFailedException;
import pl.karoldominiak.example.application.utils.DateParser;

import java.time.LocalDate;

@Slf4j
@Getter
public class CreateProfileCommand {

    private final String firstName;
    private final String lastName;
    private String email;
    private Long phoneArea;
    private Long phoneNumber;
    private LocalDate dateOfBirth;

    public CreateProfileCommand(final String firstName,
                                final String lastName,
                                final String email,
                                final Long phoneArea,
                                final Long phoneNumber,
                                final String dateOfBirth) throws ParsingDateFailedException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneArea = phoneArea;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = DateParser.parse(dateOfBirth);
    }
}
