package com.lite.hris.employee;

import com.lite.hris.employee.status.EmployeeStatus;
import com.lite.hris.employee.status.EmployeeStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRegistrationService {
    private final EmployeeRepository repository;
    private final EmployeeStatusRepository statusRepository;
    private final RegistrationNumberValidationService registrationNumberValidationService;

    public void registerNumber(long id, NumberRegistrationDTO form){
        boolean valid = registrationNumberValidationService.isValid(form.getNumber());
        if(!valid)
            throw new RuntimeException("Rules are not match");

        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            employee.registrationNumber(form);
            repository.save(employee);
        }else throw new RuntimeException("Employee is not found");
    }

    public void register(EmployeeJoinDTO form){
        Employee e = new Employee(form);
        repository.save(e);

        EmployeeStatus employeeStatus = new EmployeeStatus(e, form);
        statusRepository.save(employeeStatus);
    }
}
