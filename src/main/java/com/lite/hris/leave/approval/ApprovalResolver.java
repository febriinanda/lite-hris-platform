package com.lite.hris.leave.approval;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import com.lite.hris.leave.approval.flow.ApprovalFlowItem;
import com.lite.hris.leave.approval.flow.FlowType;
import com.lite.hris.leave.approval.group.ApprovalGroup;
import com.lite.hris.leave.approval.group.ApprovalGroupItem;
import com.lite.hris.leave.approval.group.ApprovalGroupItemRepository;
import com.lite.hris.leave.approval.group.ApprovalGroupRepository;
import com.lite.hris.leave.approval.task.ApprovalTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApprovalResolver {
    private final EmployeeRepository employeeRepository;
    private final ApprovalGroupRepository approvalGroupRepository;
    private final ApprovalGroupItemRepository approvalGroupItemRepository;
    public List<ApprovalTask> resolve(Employee requester, ApprovalFlowItem item){
        List<ApprovalTask> tasks = new ArrayList<>();
        if(item.getType().equals(FlowType.EXACT_EMPLOYEE)){
            Optional<Employee> byId = employeeRepository.findById(item.getReferenceId());
            if(byId.isPresent()){
                Employee employee = byId.get();
                ApprovalTask t = new ApprovalTask();
                t.setEmployee(employee);
                t.setSequence(1);
                tasks.add(t);
            }
        }

        if (item.getType().equals(FlowType.GROUP)){
            Optional<ApprovalGroup> byId = approvalGroupRepository.findById(item.getReferenceId());
            if(byId.isPresent()){
                ApprovalGroup group = byId.get();
                List<ApprovalGroupItem> byHeader = approvalGroupItemRepository.findByHeader(group);
                for (ApprovalGroupItem i : byHeader) {
                    ApprovalTask t = new ApprovalTask();
                    t.setEmployee(i.getEmployee());
                    t.setSequence(1);
                    tasks.add(t);
                }
            }
        }

        return tasks;
    }
}
