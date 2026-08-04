package com.lite.hris.office;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService service;

    @GetMapping
    public List<Office> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Office findById(@PathVariable long id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody OfficeDTO form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody OfficeDTO form){
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
