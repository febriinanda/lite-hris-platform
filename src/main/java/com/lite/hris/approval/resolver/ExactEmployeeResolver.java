package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExactEmployeeResolver implements ApprovalResolver{
    private final EmployeeRepository employeeRepository;

    @Override
    public FlowType getType() {
        return FlowType.EXACT_EMPLOYEE;
    }

    @Override
    public List<Employee> resolve(Employee requester, ApprovalFlowItem item) {
        List<Employee> approvals = new ArrayList<>();
        Optional<Employee> byId = employeeRepository.findById(item.getReferenceId());
        if(byId.isPresent()){
            Employee employee = byId.get();
            approvals.add(employee);
        }
        return approvals;
    }
}
