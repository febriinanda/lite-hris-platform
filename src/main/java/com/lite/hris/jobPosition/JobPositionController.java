package com.lite.hris.jobPosition;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job/position")
@RequiredArgsConstructor
public class JobPositionController {
    private final JobPositionService service;

    @GetMapping
    public List<JobPosition> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public JobPosition findById(@PathVariable long id){
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody JobPositionDTO form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody JobPositionDTO form){
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
