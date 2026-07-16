package com.lite.hris.fact.leave;

import com.lite.hris.employee.attendance.EmployeeAttendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveFactService {
    private final LeaveFactRepository leaveFactRepository;

    public void calculate(EmployeeAttendance ea){
        LeaveFact fact = leaveFactRepository.findByEmployeeAndAttendanceDate(ea.getSchedule().getEmployee(), ea.getAttendanceDate());
        ea.update(fact);
    }
}
