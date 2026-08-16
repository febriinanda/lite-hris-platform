package com.lite.hris.employee.shiftPattern;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeShiftPatternService {
    private final EmployeeService employeeService;
    private final EmployeeShiftPatternRepository employeeShiftPatternRepository;
    public List<EmployeeShiftPattern> findByEmployee(long id){
        Employee employee = employeeService.findById(id);
        return employeeShiftPatternRepository.findByEmployee(employee);
    }

    public void register(long id, EmployeeShiftPatternDTO form){
        Employee employee = employeeService.findById(id);
        EmployeeShiftPattern p = new EmployeeShiftPattern(form, employee);
        employeeShiftPatternRepository.save(p);
    }
}
