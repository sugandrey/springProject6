package com.sugandrey.FirstRestApp.controllers;

import com.sugandrey.FirstRestApp.dto.PersonDTO;
import com.sugandrey.FirstRestApp.models.Person;
import com.sugandrey.FirstRestApp.services.PeopleService;
import com.sugandrey.FirstRestApp.util.PersonErrorResponse;
import com.sugandrey.FirstRestApp.util.PersonNotCreatedException;
import com.sugandrey.FirstRestApp.util.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(final PeopleService peopleService, final ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());  // jackson конвертирует объекты в json
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return convertToPersonDTO(peopleService.findOne(id)); // jackson конвертирует объекты в json
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid final PersonDTO personDTO, final BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                errMsg.append(error.getField())
                      .append(" - ")
                      .append(error.getDefaultMessage())
                      .append(";");
            }
            throw new PersonNotCreatedException(errMsg.toString());
        }
        peopleService.save(convertToPerson(personDTO));
        // отправляем ответ с пустым телом и статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler()
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        final PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id was not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        final PersonErrorResponse response = new PersonErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(final PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
    private PersonDTO convertToPersonDTO(final Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
