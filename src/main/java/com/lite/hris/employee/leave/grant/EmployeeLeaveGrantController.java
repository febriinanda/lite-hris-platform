package com.lite.hris.employee.leave.grant;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransaction;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransactionRepository;
import com.lite.hris.employee.leave.transaction.LeaveReferenceType;
import com.lite.hris.employee.leave.transaction.LeaveTransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeLeaveGrantController {
    private final EmployeeLeaveGrantRepository employeeLeaveGrantRepository;
    private final EmployeeLeaveTransactionRepository employeeLeaveTransactionRepository;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/{id}/leave/grant")
    public void grant(@PathVariable long id, @RequestBody LeaveBalanceGrantRequest form){
        Optional<Employee> byId = employeeRepository.findById(id);
        if(byId.isPresent()){
            List<EmployeeLeaveGrant> existed = employeeLeaveGrantRepository.findByEmployeeAndYear(byId.get(), form.getYear());
            if(!existed.isEmpty())
                throw new RuntimeException("This employee has beed granted this year");

            EmployeeLeaveGrant grant = new EmployeeLeaveGrant(form);
            EmployeeLeaveTransaction t = new EmployeeLeaveTransaction();
            t.setEmployee(form.getEmployee());
            t.setGrant(grant);
            t.setAmount(form.getGrantedDays());
            t.setTransactionType(LeaveTransactionType.ENTITLEMENT);
            t.setReferenceType(LeaveReferenceType.SYSTEM);
            t.setCreatedAt(LocalDateTime.now());
            employeeLeaveGrantRepository.save(grant);
            employeeLeaveTransactionRepository.save(t);
        }else throw new RuntimeException("This employee is not found");
    }
}
