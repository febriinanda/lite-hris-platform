package com.lite.hris.employee.leave.grant;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeService;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransaction;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransactionRepository;
import com.lite.hris.employee.leave.transaction.LeaveReferenceType;
import com.lite.hris.employee.leave.transaction.LeaveTransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeLeaveGrantService {
    private final EmployeeLeaveGrantRepository employeeLeaveGrantRepository;
    private final EmployeeLeaveTransactionRepository employeeLeaveTransactionRepository;
    private final EmployeeService employeeService;
    public void grant(long id, LeaveBalanceGrantRequest form){
        Employee employee = employeeService.findById(id);
        List<EmployeeLeaveGrant> existed = employeeLeaveGrantRepository.findByEmployeeAndYear(employee, form.getYear());
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
    }
}
