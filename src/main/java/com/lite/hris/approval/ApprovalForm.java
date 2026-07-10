package com.lite.hris.approval;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.approval.task.ApprovalStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovalForm {
    private ApprovalStatus status;
    private long approvalTaskId;
}
