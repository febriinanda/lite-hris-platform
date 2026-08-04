package com.lite.hris.office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository repository;

    public List<Office> findAll(){
        return repository.findAll();
    }

    public Office findById(long id){
        Optional<Office> byId = repository.findById(id);
        if(byId.isPresent()) return byId.get();
        else throw new RuntimeException("Office is not found");
    }

    public void create(OfficeDTO form){
        Office o = new Office(form);
        repository.save(o);
    }

    public void update(long id, OfficeDTO form) {
        Office office = findById(id);
        office.update(form);
    }

    public void delete(long id){
        Office office = findById(id);
        office.delete();
    }
}
