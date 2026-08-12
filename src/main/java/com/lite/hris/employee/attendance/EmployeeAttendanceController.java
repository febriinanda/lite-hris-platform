package com.lite.hris.employee.attendance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeAttendanceController {
    private final DailyAttendanceService service;

    @GetMapping("/{id}/attendances")
    public List<DailyAttendance> getAttendances(
            @PathVariable long id,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to){
        return service.getAttendances(id, from, to);
    }

}
