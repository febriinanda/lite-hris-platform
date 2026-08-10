package com.lite.hris.employee.schedule;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long> {
    List<EmployeeSchedule> findByEmployeeAndScheduleDateBetween(Employee employee, LocalDate s, LocalDate e);

    List<EmployeeSchedule> findByScheduleDate(LocalDate scheduleDate);

    EmployeeSchedule findByEmployeeAndScheduleDate(Employee employee, LocalDate date);
}
