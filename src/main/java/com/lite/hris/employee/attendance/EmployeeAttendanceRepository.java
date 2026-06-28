package com.lite.hris.employee.attendance;

import com.lite.hris.employee.schedule.EmployeeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    List<EmployeeAttendance> findByScheduleIn(List<EmployeeSchedule> schedules);
}
