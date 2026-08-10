package com.lite.hris.attendance;

import com.lite.hris.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    List<AttendanceLog> findByTimeBetween(LocalDateTime min, LocalDateTime max);

    List<AttendanceLog> findByEmployeeAndTimeBetween(Employee employee, LocalDateTime min, LocalDateTime max);
}
