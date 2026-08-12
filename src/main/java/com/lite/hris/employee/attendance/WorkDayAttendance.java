package com.lite.hris.employee.attendance;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class WorkDayAttendance implements DailyAttendance{
    private LocalDate date;
    private LocalDateTime scheduleIn;
    private LocalDateTime scheduleOut;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;

    public WorkDayAttendance(EmployeeAttendance attendance) {
        this.date = attendance.getAttendanceDate();
        this.scheduleIn = attendance.getSchedule().getStartDate();
        this.scheduleOut = attendance.getSchedule().getEndDate();
        this.clockIn = attendance.getClockIn();
        this.clockOut = attendance.getClockOut();
    }
}
