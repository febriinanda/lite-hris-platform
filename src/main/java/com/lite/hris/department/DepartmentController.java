package com.lite.hris.department;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository repository;

    @GetMapping
    public List<Department> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable long id){
        Optional<Department> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Department is not found");
    }

    @PostMapping
    public void create(@RequestBody DepartmentDTO form){
        Department d = new Department(form);
        repository.save(d);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody DepartmentDTO form){
        Optional<Department> byId = repository.findById(id);
        if(byId.isPresent()){
            Department existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Department is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<Department> byId = repository.findById(id);
        if(byId.isPresent()){
            Department existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Department is not found");
    }
}
