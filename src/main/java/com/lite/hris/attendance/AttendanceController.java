package com.lite.hris.attendance;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.attendance.*;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrant;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrantRepository;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransaction;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransactionRepository;
import com.lite.hris.employee.leave.transaction.LeaveReferenceType;
import com.lite.hris.employee.leave.transaction.LeaveTransactionType;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    private final AttendanceLogRepository attendanceLogRepository;
    private final EmployeeLeaveGrantRepository employeeLeaveGrantRepository;
    private final EmployeeLeaveTransactionRepository employeeLeaveTransactionRepository;
    @PostMapping("/process")
    public void process(@RequestBody AttendanceProcessRequest form){
        List<EmployeeSchedule> byScheduleDate = employeeScheduleRepository.findByScheduleDate(form.getScheduleDate());
        Map<Long, EmployeeAttendance> attendanceMap = employeeAttendanceRepository.findByScheduleIn(byScheduleDate).stream()
                .collect(Collectors.toMap(o -> o.getSchedule().getId(), Function.identity()));

        LocalDateTime min = LocalDateTime.MAX;
        LocalDateTime max = LocalDateTime.MIN;

        for (EmployeeSchedule s : byScheduleDate) {
            if(s.isOff())
                continue;

            min = s.getStartDate().isBefore(min)?s.getStartDate():min;
            max = s.getEndDate().isAfter(max)?s.getEndDate():max;
        }

        Map<Employee, List<AttendanceLog>> attendanceLogsPerEmployee = attendanceLogRepository.findByTimeBetween(min.minusHours(2), max.plusHours(4))
                .stream().collect(Collectors.groupingBy(AttendanceLog::getEmployee));

        List<EmployeeAttendance> changes = new ArrayList<>();
        for (EmployeeSchedule s : byScheduleDate) {
            List<AttendanceLog> logs = attendanceLogsPerEmployee.getOrDefault(s.getEmployee(), new ArrayList<>());
            EmployeeAttendance a = attendanceMap.get(s.getId());
            if(a == null){
                a = new EmployeeAttendance(s, VerificationStatus.PENDING);
            }

            a.updateClock(logs);
            a.check();
            changes.add(a);
        }

        employeeAttendanceRepository.saveAll(changes);
    }

    @PatchMapping("/{id}/verify")
    public void verify(@PathVariable long id, @RequestBody AttendanceVerificationRequest form){
        Optional<EmployeeAttendance> byId = employeeAttendanceRepository.findById(id);
        if(byId.isPresent()){
            EmployeeAttendance a = byId.get();
            a.verified(form);

            if(a.getVerificationStatus() == VerificationStatus.VERIFIED || a.getVerificationStatus() == VerificationStatus.AUTO_VERIFIED){
                if(a.getAction() == AttendanceFollowUp.LEAVE_DEDUCTION){
                    Optional<EmployeeLeaveGrant> min = employeeLeaveGrantRepository.findByEmployee(a.getSchedule().getEmployee()).stream().filter(
                            o -> o.getEarnedDate().isBefore(a.getAttendanceDate()) && o.getFinalExpireDate().isAfter(a.getAttendanceDate())
                    ).min(Comparator.comparing(EmployeeLeaveGrant::getFinalExpireDate));

                    EmployeeLeaveTransaction t = new EmployeeLeaveTransaction();
                    t.setCreatedAt(LocalDateTime.now());
                    t.setReferenceType(LeaveReferenceType.ATTENDANCE);
                    t.setReferenceId(a.getId());
                    t.setEmployee(a.getSchedule().getEmployee());
                    t.setAmount(-1);
                    LeaveTransactionType type = null;
                    if(a.getStatus() == AttendanceStatus.ABSENT || a.getStatus() == AttendanceStatus.INCOMPLETE)
                        type = LeaveTransactionType.ABSENCE_CONVERSION;
                    else if(a.getStatus() == AttendanceStatus.LEAVE)
                        type = LeaveTransactionType.LEAVE_APPROVED;
                    t.setTransactionType(type);
                    t.setCreatedBy(form.getVerifiedBy().getPerson().getName());

                    if(min.isPresent()){
                        EmployeeLeaveGrant grant = min.get();
                        t.setGrant(grant);
                        grant.setRemainingDays(grant.getRemainingDays() + t.getAmount());
                        employeeLeaveGrantRepository.save(grant);
                    }

                    employeeLeaveTransactionRepository.save(t);
                }
            }
            employeeAttendanceRepository.save(a);


        }else throw new RuntimeException("This attendance is not exist");
    }

    @PostMapping("/clock")
    public void clock(@RequestBody AttendanceClockRequest form){
        AttendanceLog log = new AttendanceLog(form);
        attendanceLogRepository.save(log);
    }
}
