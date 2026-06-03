package com.lite.hris.employee.workSite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeWorkSiteRepository extends JpaRepository<EmployeeWorkSite, Long> {
}
