package com.lite.hris.department;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping
    public List<DepartmentResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentResponse findById(@PathVariable long id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody DepartmentDTO form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody DepartmentDTO form){
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
