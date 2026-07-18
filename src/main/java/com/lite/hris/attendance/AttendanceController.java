package com.lite.hris.attendance;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.attendance.EmployeeAttendance;
import com.lite.hris.employee.attendance.EmployeeAttendanceRepository;
import com.lite.hris.employee.attendance.VerificationStatus;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleRepository;
import com.lite.hris.employee.attendance.*;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrant;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrantRepository;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransaction;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransactionRepository;
import com.lite.hris.employee.leave.transaction.LeaveReferenceType;
import com.lite.hris.employee.leave.transaction.LeaveTransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    private final AttendanceLogRepository attendanceLogRepository;
    private final AttendanceVerificationService attendanceVerificationService;


    private final ApplicationEventPublisher publisher;
    private final AttendanceProcessService attendanceProcessService;
    private final AttendanceClockService attendanceClockService;
    @PostMapping("/process")
    public void process(@RequestBody AttendanceProcessRequest form){
        attendanceProcessService.process(form);
    }

    @PatchMapping("/{id}/verify")
    public void verify(@PathVariable long id, @RequestBody AttendanceVerificationRequest form){
        attendanceVerificationService.verify(id, form);
    }

    @PostMapping("/clock")
    public void clock(@RequestBody AttendanceClockRequest form){
        AttendanceLog log = attendanceClockService.clock(form);
        publisher.publishEvent(new AttendanceChangedEvent(log.getEmployee().getId(), log.getTime().toLocalDate()));
    }
}
