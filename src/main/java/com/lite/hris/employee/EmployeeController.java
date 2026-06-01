package com.lite.hris.employee;

import com.lite.hris.employee.position.AssignmentDTO;
import com.lite.hris.employee.position.EmployeePosition;
import com.lite.hris.employee.position.EmployeePositionRepository;
import com.lite.hris.employee.status.EmployeeStatus;
import com.lite.hris.employee.status.EmployeeStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeePositionRepository positionRepository;
    private final EmployeeStatusRepository statusRepository;

    @GetMapping
    public List<Employee> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Employee is not found");
    }

    @PostMapping
    public void create(@RequestBody EmployeeJoinDTO form){
        Employee e = new Employee(form);
        repository.save(e);

        EmployeeStatus employeeStatus = new EmployeeStatus(e, form);
        statusRepository.save(employeeStatus);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody EmployeeResignDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee e = byId.get();
            e.update(form);
            repository.save(e);
        }else throw new RuntimeException("Employee is not found");
    }

    @PostMapping("/{id}/assign")
    public void assign(@PathVariable long id, @RequestBody AssignmentDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            EmployeePosition position = new EmployeePosition(byId.get(), form);
            positionRepository.save(position);
        }else throw new RuntimeException("Employee is not found");
    }
}
