package com.lite.hris.employee.shiftPattern;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeShiftPatternService {
    private final EmployeeRepository repository;
    private final EmployeeShiftPatternRepository employeeShiftPatternRepository;
    public List<EmployeeShiftPattern> findByEmployee(long id){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            return employeeShiftPatternRepository.findByEmployee(employee);
        }else throw new RuntimeException("This employee is not found");
    }

    public void register(long id, EmployeeShiftPatternDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            EmployeeShiftPattern p = new EmployeeShiftPattern(form, employee);
            employeeShiftPatternRepository.save(p);
        }else throw new RuntimeException("This employee is not found");
    }
}
