package com.lite.hris.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;

    public List<Employee> findAll(){
        return repository.findAll();
    }

    public Employee findById(long id){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Employee is not found");
    }

    public void save(Employee e){
        repository.save(e);
    }
}
