package com.lite.hris.jobPosition;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job/position")
@RequiredArgsConstructor
public class JobPositionController {
    private final JobPositionRepository repository;

    @GetMapping
    public List<JobPosition> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public JobPosition findById(@PathVariable long id){
        Optional<JobPosition> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Job Position is not found");
    }

    @PostMapping
    public void create(@RequestBody JobPositionDTO form){
        JobPosition position = new JobPosition(form);
        repository.save(position);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody JobPositionDTO form){
        Optional<JobPosition> byId = repository.findById(id);
        if(byId.isPresent()){
            JobPosition existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Job Position is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<JobPosition> byId = repository.findById(id);
        if(byId.isPresent()){
            JobPosition existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Job Position is not found");
    }
}
