package com.lite.hris.approval.group.resolver;

import com.lite.hris.approval.group.ApprovalGroup;
import com.lite.hris.approval.group.ApprovalMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnyApprovalResolver implements ApprovalCountResolver{

    @Override
    public ApprovalMode getMode() {
        return ApprovalMode.ANY;
    }

    @Override
    public int resolve(ApprovalGroup group) {
        return group.getMinimumApproval();
    }
}
