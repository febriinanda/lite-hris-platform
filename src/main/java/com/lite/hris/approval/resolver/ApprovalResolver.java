package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.employee.Employee;

import java.util.List;

public interface ApprovalResolver {
    FlowType getType();
    List<Employee> resolve(Employee requester, ApprovalFlowItem item);
}
