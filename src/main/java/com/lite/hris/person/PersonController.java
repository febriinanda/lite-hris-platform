package com.lite.hris.person;

import com.lite.hris.FileUpload.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository repository;
    private final FileUploadService fileUploadService;

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

    @PatchMapping("/{id}/name")
    public void rename(@PathVariable long id, @RequestBody PersonDTO form){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            Person existed = byId.get();
            existed.setName(form.getName());
            repository.save(existed);
        }else throw new RuntimeException("Person is not found");
    }

    @PatchMapping("/{id}/birthday")
    public void birthday(@PathVariable long id, @RequestBody PersonDTO form){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            Person existed = byId.get();
            existed.setBirthDate(form.getBirthDate());
            repository.save(existed);
        }else throw new RuntimeException("Person is not found");
    }

    @PatchMapping("/{id}/gender")
    public void gender(@PathVariable long id, @RequestBody PersonDTO form){
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            Person existed = byId.get();
            existed.setGender(form.getGender());
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

    @PatchMapping("/{id}/photo/profile")
    public void uploadPhotoProfile(@PathVariable long id, @RequestPart("file")MultipartFile file) throws IOException {
        Optional<Person> byId = repository.findById(id);
        if(byId.isPresent()){
            Person person = byId.get();
            fileUploadService.photoProfile(person, file);
            repository.save(person);
        }
    }
}
