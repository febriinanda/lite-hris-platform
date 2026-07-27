package com.lite.hris.employee.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeScheduleController {
    private final EmployeeScheduleGeneratorService scheduleGeneratorService;
    private final EmployeeScheduleService scheduleService;

    @GetMapping("/{id}/schedule")
    public List<EmployeeSchedule> getSchedules(
            @PathVariable long id,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end){
        return scheduleService.getSchedules(id, start, end);
    }

    @PostMapping("/{id}/schedule/generate")
    public void generate(@PathVariable long id, @RequestBody GenerateShiftDTO form){
        scheduleGeneratorService.generate(id, form);
    }
}
