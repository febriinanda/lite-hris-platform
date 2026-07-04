package com.lite.hris.employee.leave.grant;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLeaveGrantRepository extends JpaRepository<EmployeeLeaveGrant, Long> {
    List<EmployeeLeaveGrant> findByEmployeeAndYear(Employee employee, int year);
}
