package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.employee.Employee;

public interface ApprovalResolver {
    FlowType getType();
    ApprovalResolved resolve(Employee requester, ApprovalFlowItem item);
}
