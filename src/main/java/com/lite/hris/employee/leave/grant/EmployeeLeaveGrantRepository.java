package com.lite.hris.employee.leave.grant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLeaveGrantRepository extends JpaRepository<EmployeeLeaveGrant, Long> {
}
