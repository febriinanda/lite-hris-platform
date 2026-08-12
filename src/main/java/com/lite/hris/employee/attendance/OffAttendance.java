package com.lite.hris.employee.attendance;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OffAttendance implements DailyAttendance{
    private LocalDate date;
    private DayType type;

    public OffAttendance(EmployeeAttendance attendance) {
        this.date = attendance.getAttendanceDate();
        this.type = attendance.getDayType();
    }
}
