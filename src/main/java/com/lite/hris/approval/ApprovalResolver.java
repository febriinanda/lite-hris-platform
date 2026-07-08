package com.lite.hris.approval;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.approval.group.ApprovalGroup;
import com.lite.hris.approval.group.ApprovalGroupItem;
import com.lite.hris.approval.task.ApprovalTask;
import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import com.lite.hris.employee.reportingLine.EmployeeReportingLine;
import com.lite.hris.employee.reportingLine.EmployeeReportingLineRepository;
import com.lite.hris.approval.group.ApprovalGroupItemRepository;
import com.lite.hris.approval.group.ApprovalGroupRepository;
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
    private final EmployeeReportingLineRepository employeeReportingLineRepository;
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

        if(item.getType().equals(FlowType.LINE_MANAGER)){
            Employee current = requester;
            for(int i = 0; i < item.getReferenceId(); i++){
                EmployeeReportingLine employeeReportingLine = employeeReportingLineRepository.findByEmployee(current);
                current = employeeReportingLine.getManager();

                if(current == null)
                    break;
            }

            if(current!=null && !current.equals(requester)){
                ApprovalTask t = new ApprovalTask();
                t.setEmployee(current);
                t.setSequence(1);
                tasks.add(t);
            }
        }

        return tasks;
    }
}
