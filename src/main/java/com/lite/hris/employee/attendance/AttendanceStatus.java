package com.lite.hris.employee.attendance;

import lombok.Getter;

@Getter
public enum AttendanceStatus {
    PRESENT,
    LATE,
    ABSENT,
    LEAVE,
    SICK,
    HOLIDAY,
    OFF,
    INCOMPLETE
}
