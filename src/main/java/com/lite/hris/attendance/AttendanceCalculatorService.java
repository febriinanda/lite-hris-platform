package com.lite.hris.attendance;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import com.lite.hris.employee.attendance.EmployeeAttendance;
import com.lite.hris.employee.attendance.EmployeeAttendanceRepository;
import com.lite.hris.employee.attendance.VerificationStatus;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleRepository;
import com.lite.hris.fact.leave.LeaveFactService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceCalculatorService {
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    private final AttendanceLogRepository attendanceLogRepository;
    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveFactService leaveFactService;

    @EventListener
    public void onAttendanceChanged(AttendanceChangedEvent event){
        this.calculate(event.getEmployeeId(), event.getDate());
    }

    @Transactional
    public void calculate(long employeeId, LocalDate date){
        Employee employee = loadEmployee(employeeId);;
        EmployeeAttendance a = loadEmployeeAttendance(employee, date);
        List<AttendanceLog> logs = loadAttendanceLog(employee, a);
        applyClock(a, logs);
        leaveFactService.calculate(a);
        employeeAttendanceRepository.save(a);
    }

    private void applyClock(EmployeeAttendance a, List<AttendanceLog> logs) {
        a.updateClock(logs);
        a.check();
    }

    private List<AttendanceLog> loadAttendanceLog(Employee employee, EmployeeAttendance a) {
        LocalDateTime min = a.getSchedule().getStartDate().minusHours(2);
        LocalDateTime max = a.getSchedule().getEndDate().plusHours(4);
        return attendanceLogRepository.findByEmployeeAndTimeBetween(employee, min, max);
    }

    private EmployeeAttendance loadEmployeeAttendance(Employee employee, LocalDate date) {
        List<EmployeeAttendance> existed = employeeAttendanceRepository.findEmployeeAndAttendanceDate(employee, date);
        if(existed.size()>1)
            throw new RuntimeException("Employee attendance is more than 1");

        if(existed.isEmpty()){
            EmployeeSchedule schedule = employeeScheduleRepository.findByEmployeeAndScheduleDate(employee, date);
            return new EmployeeAttendance(schedule, VerificationStatus.PENDING);
        }else{
            return existed.get(0);
        }
    }

    private Employee loadEmployee(long employeeId) {
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        if(byId.isEmpty())
            throw new RuntimeException("This employee is not found");

        return byId.get();
    }
}
