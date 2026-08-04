package com.lite.hris.jobPosition;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobPositionService {
    private final JobPositionRepository repository;

    public List<JobPosition> findAll(){
        return repository.findAll();
    }

    public JobPosition findById(long id){
        Optional<JobPosition> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Job Position is not found");
    }

    public void create(JobPositionDTO form) {
        JobPosition position = new JobPosition(form);
        repository.save(position);
    }

    public void update(long id, JobPositionDTO form) {
        JobPosition jp = findById(id);
        jp.update(form);
    }

    public void delete(long id) {
        JobPosition jp = findById(id);
        jp.delete();
    }
}
