package com.lite.hris.employee.reportingLine;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReportingLineRepository extends JpaRepository<EmployeeReportingLine, Long> {
    EmployeeReportingLine findByEmployee(Employee current);
}
