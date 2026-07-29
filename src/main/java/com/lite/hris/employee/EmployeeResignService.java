package com.lite.hris.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeResignService {
    private final EmployeeRepository repository;
    public void resign(long id, EmployeeResignDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee e = byId.get();
            e.update(form);
            repository.save(e);
        }else throw new RuntimeException("Employee is not found");
    }
}
