package com.lite.hris.office;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeRepository repository;

    @GetMapping
    public List<Office> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Office findById(@PathVariable long id){
        Optional<Office> byId = repository.findById(id);
        if(byId.isPresent()) return byId.get();
        else throw new RuntimeException("Office is not found");
    }

    @PostMapping
    public void create(@RequestBody OfficeDTO form){
        Office o = new Office(form);
        repository.save(o);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody OfficeDTO form){
        Optional<Office> byId = repository.findById(id);
        if(byId.isPresent()){
            Office existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Office is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<Office> byId = repository.findById(id);
        if(byId.isPresent()){
            Office existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Office is not found");
    }
}
