package com.lite.hris.fact.leave;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LeaveFactRepository extends JpaRepository<LeaveFact, Long> {
    LeaveFact findByEmployeeAndAttendanceDate(Employee employee, LocalDate attendanceDate);
}
