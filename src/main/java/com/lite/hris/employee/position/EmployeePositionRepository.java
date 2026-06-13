package com.lite.hris.employee.position;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
    List<EmployeePosition> findByEmployee(Employee employee);
}
