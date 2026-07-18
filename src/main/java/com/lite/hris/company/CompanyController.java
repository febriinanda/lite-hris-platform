package com.lite.hris.company;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService service;

    @GetMapping
    public List<CompanyResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CompanyResponse findById(@PathVariable long id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody CompanyDTO form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody CompanyDTO form){
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
