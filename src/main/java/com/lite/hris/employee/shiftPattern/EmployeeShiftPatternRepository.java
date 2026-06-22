package com.lite.hris.employee.shiftPattern;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeShiftPatternRepository extends JpaRepository<EmployeeShiftPattern, Long> {
    List<EmployeeShiftPattern> findByEmployee(Employee employee);
}
