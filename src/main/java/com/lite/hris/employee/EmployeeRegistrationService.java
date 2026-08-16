package com.lite.hris.employee;

import com.lite.hris.employee.status.EmployeeStatus;
import com.lite.hris.employee.status.EmployeeStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeRegistrationService {
    private final EmployeeService employeeService;
    private final EmployeeStatusRepository statusRepository;
    private final RegistrationNumberValidationService registrationNumberValidationService;

    public void registerNumber(long id, NumberRegistrationDTO form){
        boolean valid = registrationNumberValidationService.isValid(form.getNumber());
        if(!valid)
            throw new RuntimeException("Rules are not match");

        Employee employee = employeeService.findById(id);
        employee.registrationNumber(form);
    }

    public void register(EmployeeJoinDTO form){
        Employee e = new Employee(form);
        employeeService.save(e);

        EmployeeStatus employeeStatus = new EmployeeStatus(e, form);
        statusRepository.save(employeeStatus);
    }
}
