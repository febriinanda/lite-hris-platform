package com.lite.hris.employee.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeScheduleController {
    @PostMapping("/{id}/generate")
    public void generate(@PathVariable long id, @RequestBody GenerateShiftDTO form){

    }
}
