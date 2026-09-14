package com.lite.hris.approval.group.resolver;

import com.lite.hris.approval.group.ApprovalGroup;
import com.lite.hris.approval.group.ApprovalGroupItem;
import com.lite.hris.approval.group.ApprovalGroupItemRepository;
import com.lite.hris.approval.group.ApprovalMode;
import com.lite.hris.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AllApprovalResolver implements ApprovalCountResolver{
    private final ApprovalGroupItemRepository approvalGroupItemRepository;
    @Override
    public ApprovalMode getMode() {
        return ApprovalMode.ALL;
    }

    @Override
    public int resolve(ApprovalGroup group) {
        List<ApprovalGroupItem> byHeader = approvalGroupItemRepository.findByHeader(group);
        List<Employee> employee = new ArrayList<>();
        for (ApprovalGroupItem i : byHeader) {
            employee.add(i.getEmployee());
        }
        return employee.size();
    }
}
