package com.lite.hris.branch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchRepository repository;

    @GetMapping
    public List<Branch> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable long id){
        Optional<Branch> byId = repository.findById(id);
        if(byId.isPresent()) return byId.get();
        else throw new RuntimeException("Branch is not found");
    }

    @PostMapping
    public void create(@RequestBody BranchDTO form){
        Branch b = new Branch(form);
        repository.save(b);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody BranchDTO form){
        Optional<Branch> byId = repository.findById(id);
        if(byId.isPresent()){
            Branch existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Branch is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<Branch> byId = repository.findById(id);
        if(byId.isPresent()){
            Branch existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Branch is not found");
    }
}
