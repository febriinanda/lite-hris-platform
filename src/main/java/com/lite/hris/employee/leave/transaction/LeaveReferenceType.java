package com.lite.hris.employee.leave.transaction;

import lombok.Getter;

@Getter
public enum LeaveReferenceType {
    LEAVE_REQUEST,
    ATTENDANCE,
    MANUAL,
    SYSTEM
}
