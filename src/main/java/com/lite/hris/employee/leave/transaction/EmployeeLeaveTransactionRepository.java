package com.lite.hris.employee.leave.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLeaveTransactionRepository extends JpaRepository<EmployeeLeaveTransaction, Long> {
}
