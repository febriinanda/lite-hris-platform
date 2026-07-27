package com.lite.hris.employee.schedule;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeScheduleService {
    private final EmployeeRepository repository;
    private final EmployeeScheduleRepository scheduleRepository;
    public List<EmployeeSchedule> getSchedules(long id, LocalDate start, LocalDate end){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            return scheduleRepository.findByEmployeeAndScheduleDateBetween(byId.get(), start, end);
        }else throw new RuntimeException("This employee is not found");
    }
}
