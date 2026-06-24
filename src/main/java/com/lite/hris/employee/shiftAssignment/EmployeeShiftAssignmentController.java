package com.lite.hris.employee.shiftAssignment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeShiftAssignmentController {
    private final EmployeeShiftAssignmentRepository shiftAssignmentRepository;

    @PostMapping("/{id}/shift/assignment")
    public void assignment(@RequestBody ShiftAssignmentDTO form){

    }
}
