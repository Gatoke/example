package pl.karoldominiak.example.port.adapter.rest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import pl.karoldominiak.example.application.command.CreateProfileCommand;
import pl.karoldominiak.example.application.exception.ParsingDateFailedException;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateProfileRequest {

    @NotBlank
    @ApiModelProperty(name = "First name", example = "Jan", required = true)
    private String firstName;

    @NotBlank
    @ApiModelProperty(name = "Last name", example = "Kowalski", required = true)
    private String lastName;

    @ApiModelProperty(name = "E-mail", example = "example@email.com")
    private String email;

    @ApiModelProperty(name = "Phone area", example = "48")
    private Long phoneArea;

    @ApiModelProperty(name = "Phone number", example = "600500400")
    private Long phoneNumber;

    @ApiModelProperty(name = "Date of birth", example = "2010-12-20")
    private String dateOfBirth;

    public CreateProfileCommand toCommand() throws ParsingDateFailedException {
        return new CreateProfileCommand(firstName, lastName, email, phoneArea, phoneNumber, dateOfBirth);
    }
}
