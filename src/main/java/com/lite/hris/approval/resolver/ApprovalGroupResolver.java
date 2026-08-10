package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.approval.group.*;
import com.lite.hris.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public ApprovalResolved resolve(Employee requester, ApprovalFlowItem item) {
        ApprovalResolved r = new ApprovalResolved();
        Optional<ApprovalGroup> byId = approvalGroupRepository.findById(item.getReferenceId());
        if(byId.isPresent()){
            ApprovalGroup group = byId.get();

            List<ApprovalGroupItem> byHeader = approvalGroupItemRepository.findByHeader(group);
            for (ApprovalGroupItem i : byHeader) {
                r.getEmployees().add(i.getEmployee());
            }

            if(group.getMode().equals(ApprovalMode.ANY))
                r.setMinimumApproval(1);

            if(group.getMode().equals(ApprovalMode.MINIMUM))
                r.setMinimumApproval(group.getMinimumApproval());

            if(group.getMode().equals(ApprovalMode.ALL))
                r.setMinimumApproval(r.getEmployees().size());
        }
        return r;
    }
}
