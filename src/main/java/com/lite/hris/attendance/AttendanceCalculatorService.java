package com.lite.hris.attendance;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.attendance.EmployeeAttendance;
import com.lite.hris.employee.attendance.EmployeeAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceCalculatorService {
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    public void calculate(Employee employee, LocalDate date){
        List<EmployeeAttendance> existed = employeeAttendanceRepository.findEmployeeAndAttendanceDate(employee, date);

    }
}
