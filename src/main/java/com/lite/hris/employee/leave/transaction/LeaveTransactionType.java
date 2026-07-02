package com.lite.hris.employee.leave.transaction;

import lombok.Getter;

@Getter
public enum LeaveTransactionType {
    ENTITLEMENT,
    LEAVE_APPROVED,
    ABSENCE_CONVERSION,
    MANUAL_ADDITION,
    MANUAL_DEDUCTION,
    EXPIRED,
    EXPIRY_REVERSAL
}
