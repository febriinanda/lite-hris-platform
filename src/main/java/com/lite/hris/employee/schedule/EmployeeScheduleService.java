package com.lite.hris.employee.schedule;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeScheduleService {
    private final EmployeeScheduleRepository scheduleRepository;
    private final EmployeeService employeeService;
    public List<EmployeeSchedule> getSchedules(long id, LocalDate start, LocalDate end){
        Employee employee = employeeService.findById(id);
        return scheduleRepository.findByEmployeeAndScheduleDateBetween(employee, start, end);
    }
}
