package com.lite.hris.attendance;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.attendance.EmployeeAttendance;
import com.lite.hris.employee.attendance.EmployeeAttendanceRepository;
import com.lite.hris.employee.attendance.VerificationStatus;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceCalculatorService {
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    private final AttendanceLogRepository attendanceLogRepository;
    private final EmployeeScheduleRepository employeeScheduleRepository;
    public void calculate(Employee employee, LocalDate date){
        List<EmployeeAttendance> existed = employeeAttendanceRepository.findEmployeeAndAttendanceDate(employee, date);
        if(existed.size()>1)
            throw new RuntimeException("Employee attendance is more than 1");

        LocalDateTime min;
        LocalDateTime max;
        EmployeeAttendance a;
        if(existed.isEmpty()){
            EmployeeSchedule schedule = employeeScheduleRepository.findByEmployeeAndScheduleDate(employee, date);
            min = schedule.getStartDate().minusHours(2);
            max = schedule.getEndDate().plusHours(4);
            a = new EmployeeAttendance(schedule, VerificationStatus.PENDING);
        }else{
            a = existed.get(0);
            min = a.getSchedule().getStartDate().minusHours(2);
            max = a.getSchedule().getEndDate().plusHours(4);
        }

        List<AttendanceLog> logs = attendanceLogRepository.findByEmployeeAndTimeBetween(employee, min, max);
        a.updateClock(logs);
        a.check();


    }
}
