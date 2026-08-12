package com.lite.hris.approval.group.resolver;

import com.lite.hris.approval.group.ApprovalGroup;
import com.lite.hris.approval.group.ApprovalMode;

public interface ApprovalCountResolver {
    ApprovalMode getMode();
    int resolve(ApprovalGroup group);
}
