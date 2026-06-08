package com.lite.hris.employee.workSite;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeWorkSiteRepository extends JpaRepository<EmployeeWorkSite, Long> {
    List<EmployeeWorkSite> findByEmployee(Employee employee);
}
