package com.lite.hris.employee.attendance;

import lombok.Getter;

@Getter
public enum AttendanceState {
    PRESENT,
    LATE,
    EARLY_LEAVE,
    INCOMPLETE,
    ABSENT
}
