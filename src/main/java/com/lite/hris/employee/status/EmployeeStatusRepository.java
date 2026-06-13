package com.lite.hris.employee.status;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Long> {
    List<EmployeeStatus> findByEmployee(Employee employee);
}
