package com.lite.hris.employee.shiftPattern;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeShiftPatternController {
    private final EmployeeShiftPatternService service;

    @GetMapping("/{id}/shift/pattern")
    public List<EmployeeShiftPattern> findShiftPattern(@PathVariable long id){
        return service.findByEmployee(id);
    }

    @PostMapping("/{id}/shift/pattern")
    public void registerShiftPattern(@PathVariable long id, @RequestBody EmployeeShiftPatternDTO form){
        service.register(id, form);
    }
}
