package com.lite.hris.employee.leave.grant;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeLeaveGrantController {
    private final EmployeeLeaveGrantRepository employeeLeaveGrantRepository;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/{id}/leave/grant")
    public void grant(@PathVariable long id, @RequestBody LeaveBalanceGrantRequest form){
        Optional<Employee> byId = employeeRepository.findById(id);
        if(byId.isPresent()){
            EmployeeLeaveGrant grant = new EmployeeLeaveGrant(form);
            employeeLeaveGrantRepository.save(grant);
        }else throw new RuntimeException("This employee is not found");
    }
}
