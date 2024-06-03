package com.sugandrey.FirstRestApp.services;

import com.sugandrey.FirstRestApp.models.Person;
import com.sugandrey.FirstRestApp.repositories.PeopleRepository;
import com.sugandrey.FirstRestApp.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository repository;

    @Autowired
    public PeopleService(final PeopleRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }
    public Person findOne(final int id) {
        final Optional<Person> foundPerson = repository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        enrichPerson(person);
        repository.save(person);
    }
    private void enrichPerson(final Person person) {
        person.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC).toString());
        person.setUpdatedAt(OffsetDateTime.now(ZoneOffset.UTC).toString());
        person.setCreatedWho("ADMIN");
    }
}
