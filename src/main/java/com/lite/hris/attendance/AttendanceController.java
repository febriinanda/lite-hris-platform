package com.lite.hris.attendance;

import com.lite.hris.employee.attendance.EmployeeAttendance;
import com.lite.hris.employee.attendance.EmployeeAttendanceRepository;
import com.lite.hris.employee.attendance.VerificationStatus;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    @PostMapping("/process")
    public void process(@RequestBody AttendanceProcessRequest form){
        List<EmployeeSchedule> byScheduleDate = employeeScheduleRepository.findByScheduleDate(form.getScheduleDate());
        Map<Long, EmployeeAttendance> attendanceMap = employeeAttendanceRepository.findByScheduleIn(byScheduleDate).stream()
                .collect(Collectors.toMap(o -> o.getSchedule().getId(), Function.identity()));

        List<EmployeeAttendance> changes = new ArrayList<>();
        for (EmployeeSchedule s : byScheduleDate) {
            EmployeeAttendance a = attendanceMap.get(s.getId());
            if(a == null){
                a = new EmployeeAttendance(s, VerificationStatus.PENDING);
            }

            a.check();
            changes.add(a);
        }

        employeeAttendanceRepository.saveAll(changes);
    }

    @PatchMapping("/{id}/verify")
    public void verify(@PathVariable long id, @RequestBody AttendanceVerificationRequest form){
        Optional<EmployeeAttendance> byId = employeeAttendanceRepository.findById(id);
        if(byId.isPresent()){
            EmployeeAttendance a = byId.get();
            a.verified(form);
            employeeAttendanceRepository.save(a);
        }else throw new RuntimeException("This attendance is not exist");
    }
}
