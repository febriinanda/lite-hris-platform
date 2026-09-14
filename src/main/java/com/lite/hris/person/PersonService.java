package com.lite.hris.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person findById(long id){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Person is not found");
    }


    public void create(PersonDTO form) {
        Person p = new Person(form);
        repository.save(p);
    }

    @Transactional
    public void update(long id, PersonDTO form) {
        Person p = findById(id);
        p.update(form);
    }

    @Transactional
    public void rename(long id, PersonDTO form) {
        if(form.getName().isEmpty())
            throw new RuntimeException("Name is empty");

        Person p = findById(id);
        p.setName(form.getName());
    }

    @Transactional
    public void birthDayUpdate(long id, PersonDTO form) {
        if(form.getBirthDate() == null)
            throw new RuntimeException("Birth date is empty");

        Person p = findById(id);
        p.setBirthDate(form.getBirthDate());
    }

    @Transactional
    public void genderSwitch(long id, PersonDTO form) {
        if(form.getGender().isEmpty())
            throw new RuntimeException("Gender is empty");

        Person p = findById(id);
        p.setGender(form.getGender());
    }

    @Transactional
    public void delete(long id) {
        Person p = findById(id);
        p.delete();
    }
}
