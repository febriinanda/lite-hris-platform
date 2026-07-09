package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.approval.group.ApprovalGroup;
import com.lite.hris.approval.group.ApprovalGroupItem;
import com.lite.hris.approval.group.ApprovalGroupItemRepository;
import com.lite.hris.approval.group.ApprovalGroupRepository;
import com.lite.hris.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApprovalGroupResolver implements ApprovalResolver{
    private final ApprovalGroupRepository approvalGroupRepository;
    private final ApprovalGroupItemRepository approvalGroupItemRepository;
    @Override
    public FlowType getType() {
        return FlowType.GROUP;
    }

    @Override
    public List<Employee> resolve(Employee requester, ApprovalFlowItem item) {
        List<Employee> approvals = new ArrayList<>();
        Optional<ApprovalGroup> byId = approvalGroupRepository.findById(item.getReferenceId());
        if(byId.isPresent()){
            ApprovalGroup group = byId.get();
            List<ApprovalGroupItem> byHeader = approvalGroupItemRepository.findByHeader(group);
            for (ApprovalGroupItem i : byHeader) {
                approvals.add(i.getEmployee());
            }
        }
        return approvals;
    }
}
