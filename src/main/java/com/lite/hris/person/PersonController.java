package com.lite.hris.person;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService service;
    private final PhotoProfileUploaderService photoProfileUploader;

    @GetMapping
    public List<Person> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable long id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody PersonDTO form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody PersonDTO form){
        service.update(id, form);
    }

    @PatchMapping("/{id}/name")
    public void rename(@PathVariable long id, @RequestBody PersonDTO form){
        service.rename(id, form);
    }

    @PatchMapping("/{id}/birthday")
    public void birthday(@PathVariable long id, @RequestBody PersonDTO form){
        service.birthDayUpdate(id, form);
    }

    @PatchMapping("/{id}/gender")
    public void gender(@PathVariable long id, @RequestBody PersonDTO form){
        service.genderSwitch(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }

    @PatchMapping("/{id}/photo/profile")
    public void uploadPhotoProfile(@PathVariable long id, @RequestPart("file")MultipartFile file) throws IOException {
        photoProfileUploader.upload(id, file);
    }
}
