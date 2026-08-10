package com.lite.hris.employee.attendance;

import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeAttendanceController {
    private final EmployeeScheduleService scheduleService;
    private final EmployeeAttendanceRepository attendanceRepository;
    @GetMapping("/{id}/attendances")
    public List<DailyAttendance> getAttendances(
            @PathVariable long id,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to){
        List<EmployeeSchedule> schedules = scheduleService.getSchedules(id, from, to);
        List<EmployeeAttendance> byScheduleIn = attendanceRepository.findByScheduleIn(schedules);
        List<DailyAttendance> dailies = new ArrayList<>();
        for (EmployeeAttendance attendance : byScheduleIn) {
            if(attendance.getDayType() == DayType.WORKDAY){
                dailies.add(new WorkDayAttendance(attendance));
            }else{
                dailies.add(new OffAttendance(attendance));
            }
        }

        return dailies;
    }

}
