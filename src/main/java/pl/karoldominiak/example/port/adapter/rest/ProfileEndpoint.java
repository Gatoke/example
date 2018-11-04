package pl.karoldominiak.example.port.adapter.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karoldominiak.example.application.ProfileFacade;
import pl.karoldominiak.example.application.exception.GeneratingProfileIdFailedException;
import pl.karoldominiak.example.application.exception.ParsingDateFailedException;
import pl.karoldominiak.example.application.exception.ProfileReadModelNotFoundException;
import pl.karoldominiak.example.application.model.ProfileReadModel;
import pl.karoldominiak.example.domain.model.Profile;
import pl.karoldominiak.example.port.adapter.rest.model.CreateProfileRequest;

@Slf4j
@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProfileEndpoint {

    private final ProfileFacade profileFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfileReadModel> getProfile(@PathVariable("id") final Long profileId)
            throws ProfileReadModelNotFoundException {
        final ProfileReadModel profileReadModel = profileFacade.getProfileReadModel(profileId);
        return ResponseEntity.ok().body(profileReadModel);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProfile(@RequestBody final CreateProfileRequest request)
            throws GeneratingProfileIdFailedException, ParsingDateFailedException {
        final Profile profile = profileFacade.createProfile(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @ExceptionHandler(value = ProfileReadModelNotFoundException.class)
    public ResponseEntity handleProfileReadModelNotFoundException(final ProfileReadModelNotFoundException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(value = GeneratingProfileIdFailedException.class)
    public ResponseEntity handleGeneratingProfileIdFailedException(final GeneratingProfileIdFailedException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = ParsingDateFailedException.class)
    public ResponseEntity handleParsingDateFailedException(final ParsingDateFailedException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
