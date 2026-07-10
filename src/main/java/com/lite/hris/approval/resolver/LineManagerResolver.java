package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.employee.Employee;
import com.lite.hris.employee.reportingLine.EmployeeReportingLine;
import com.lite.hris.employee.reportingLine.EmployeeReportingLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LineManagerResolver implements ApprovalResolver{
    private final EmployeeReportingLineRepository employeeReportingLineRepository;
    @Override
    public FlowType getType() {
        return FlowType.LINE_MANAGER;
    }

    @Override
    public ApprovalResolved resolve(Employee requester, ApprovalFlowItem item) {
        ApprovalResolved r = new ApprovalResolved();
        Employee current = requester;
        for(int i = 0; i < item.getReferenceId(); i++){
            EmployeeReportingLine employeeReportingLine = employeeReportingLineRepository.findByEmployee(current);
            current = employeeReportingLine.getManager();

            if(current == null)
                break;
        }

        if(current!=null && !current.equals(requester)){
            r.getEmployees().add(current);
            r.setMinimumApproval(1);
        }
        return r;
    }
}
