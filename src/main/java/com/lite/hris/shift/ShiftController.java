package com.lite.hris.shift;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shift")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftRepository repository;

    @GetMapping
    public List<Shift> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Shift findById(@PathVariable long id){
        Optional<Shift> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Shift is not found");
    }

    @PostMapping
    public void create(@RequestBody ShiftDTO form){
        Shift s = new Shift(form);
        repository.save(s);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ShiftDTO form){
        Optional<Shift> byId = repository.findById(id);
        if(byId.isPresent()){
            Shift existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Shift is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<Shift> byId = repository.findById(id);
        if(byId.isPresent()){
            Shift existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Shift is not found");
    }
}
