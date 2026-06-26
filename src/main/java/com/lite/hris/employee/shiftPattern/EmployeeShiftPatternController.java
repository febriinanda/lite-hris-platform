package com.lite.hris.employee.shiftPattern;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeShiftPatternController {
    private final EmployeeRepository repository;
    private final EmployeeShiftPatternRepository employeeShiftPatternRepository;

    @GetMapping("/{id}/shift/pattern")
    public List<EmployeeShiftPattern> findShiftPattern(@PathVariable long id){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            return employeeShiftPatternRepository.findByEmployee(employee);
        }else throw new RuntimeException("This employee is not found");
    }

    @PostMapping("/{id}/shift/pattern")
    public void registerShiftPattern(@PathVariable long id, @RequestBody EmployeeShiftPatternDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            EmployeeShiftPattern p = new EmployeeShiftPattern(form, employee);
            employeeShiftPatternRepository.save(p);
        }else throw new RuntimeException("This employee is not found");
    }
}
