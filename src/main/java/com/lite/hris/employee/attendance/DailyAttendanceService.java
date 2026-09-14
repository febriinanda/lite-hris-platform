package com.lite.hris.employee.attendance;

import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.employee.schedule.EmployeeScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyAttendanceService {
    private final EmployeeScheduleService scheduleService;
    private final EmployeeAttendanceRepository attendanceRepository;
    public List<DailyAttendance> getAttendances(long id, LocalDate from, LocalDate to){
        List<EmployeeSchedule> schedules = scheduleService.getSchedules(id, from, to);
        List<EmployeeAttendance> byScheduleIn = attendanceRepository.findByScheduleIn(schedules);
        List<DailyAttendance> dailies = new ArrayList<>();
        for (EmployeeAttendance attendance : byScheduleIn) {
            dailies.add(getDaily(attendance));
        }

        return dailies;
    }

    public DailyAttendance getAttendance(long id, LocalDate date){
        List<DailyAttendance> attendances = getAttendances(id, date, date);
        if(attendances.isEmpty())
            return null;
        return attendances.getFirst();
    }

    private DailyAttendance getDaily(EmployeeAttendance attendance){
        if(attendance.getDayType() == DayType.WORKDAY){
            return new WorkDayAttendance(attendance);
        }
        return new OffAttendance(attendance);
    }
}
