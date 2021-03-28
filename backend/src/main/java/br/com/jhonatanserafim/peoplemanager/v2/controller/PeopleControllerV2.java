package br.com.jhonatanserafim.peoplemanager.v2.controller;

import br.com.jhonatanserafim.peoplemanager.jpa.domain.Person;
import br.com.jhonatanserafim.peoplemanager.jpa.repository.PersonRepository;
import br.com.jhonatanserafim.peoplemanager.v2.dto.ErrorResponseV2;
import br.com.jhonatanserafim.peoplemanager.v2.dto.PersonEntryV2;
import br.com.jhonatanserafim.peoplemanager.v2.dto.PersonResponseV2;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@RestController
@RequestMapping(value = "/v2/people", produces = APPLICATION_JSON_VALUE)
public class PeopleControllerV2 {

    private final PersonRepository repository;

    public PeopleControllerV2(PersonRepository repository) {
        this.repository = repository;
    }

    @ApiResponse(code = 200, message = "Success", response = PersonResponseV2.class, responseContainer = "List")
    @Transactional(propagation = NOT_SUPPORTED)
    @GetMapping(path = "/")
    public ResponseEntity<?> findAll() {
        List<PersonResponseV2> people = repository.findAll()
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(people);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = PersonResponseV2.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Transactional(propagation = NOT_SUPPORTED)
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = PersonResponseV2.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponseV2.class)
    })
    @Transactional(propagation = REQUIRED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> persist(@Valid @RequestBody PersonEntryV2 personEntry, @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseV2(errors));
        }

        Person person = fromEntry(new Person(), personEntry);
        repository.save(person);

        PersonResponseV2 personResponse = toResponse(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = PersonResponseV2.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponseV2.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Transactional(propagation = REQUIRED)
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PersonEntryV2 personEntry,
                                    @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseV2(errors));
        }

        return repository.findById(id)
                .map(person -> fromEntry(person, personEntry))
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = PersonResponseV2.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Transactional(propagation = REQUIRED)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return repository.findById(id)
                .map(e -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private Person fromEntry(Person person, PersonEntryV2 personEntry) {
        return person.setName(personEntry.getName())
                .setGender(personEntry.getGender())
                .setEmail(personEntry.getEmail())
                .setBirthDate(personEntry.getBirthDate())
                .setNaturalness(personEntry.getNaturalness())
                .setNationality(personEntry.getNationality())
                .setCpf(personEntry.getCpf())
                .setAddress(personEntry.getAddress());
    }

    private PersonResponseV2 toResponse(Person person) {
        return PersonResponseV2.Builder.of()
                .withId(person.getId())
                .withName(person.getName())
                .withGender(person.getGender())
                .withEmail(person.getEmail())
                .withBirthDate(person.getBirthDate())
                .withNaturalness(person.getNaturalness())
                .withNationality(person.getNationality())
                .withCpf(person.getCpf())
                .withAddress(person.getAddress())
                .withCreatedAt(person.getCreatedAt())
                .withUpdatedAt(person.getUpdatedAt())
                .build();
    }
}
