package com.lite.hris.shift;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository repository;

    public List<Shift> findAll(){
        return repository.findAll();
    }

    public Shift findById(long id){
        Optional<Shift> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Shift is not found");
    }

    public void create(ShiftDTO form){
        Shift s = new Shift(form);
        repository.save(s);
    }

    public void update(long id, ShiftDTO form){
        Shift s = findById(id);
        s.update(form);
    }

    public void delete(long id){
        Shift s = findById(id);
        s.deleted();
    }
}
