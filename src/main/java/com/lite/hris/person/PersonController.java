package com.lite.hris.person;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository repository;

    @GetMapping
    public List<Person> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable long id){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Person is not found");
    }

    @PostMapping
    public void create(@RequestBody PersonDTO form){
        Person p = new Person(form);
        repository.save(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody PersonDTO form){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            Person existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Person is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            Person existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Person is not found");
    }
}
