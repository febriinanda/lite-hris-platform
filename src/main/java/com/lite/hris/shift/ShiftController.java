package com.lite.hris.shift;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftService service;


    @GetMapping
    public List<Shift> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Shift findById(@PathVariable long id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody ShiftDTO form){
        service.create(form);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ShiftDTO form){
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
