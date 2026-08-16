package com.lite.hris.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeResignService {
    private final EmployeeService employeeService;
    public void resign(long id, EmployeeResignDTO form){
        Employee e = employeeService.findById(id);
        e.update(form);
    }
}
