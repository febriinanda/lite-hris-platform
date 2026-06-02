package com.lite.hris.company;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyRepository repository;

    @GetMapping
    public List<Company> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable long id){
        Optional<Company> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Company is not found");
    }

    @PostMapping
    public void create(@RequestBody CompanyDTO form){
        Company c = new Company(form);
        repository.save(c);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody CompanyDTO form){
        Optional<Company> byId = repository.findById(id);
        if(byId.isPresent()){
            Company existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Company is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<Company> byId = repository.findById(id);
        if(byId.isPresent()){
            Company existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Company is not found");
    }
}
