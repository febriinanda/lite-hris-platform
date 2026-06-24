package com.lite.hris.employee.shiftAssignment;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeShiftAssignmentController {
    private final EmployeeShiftAssignmentRepository shiftAssignmentRepository;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/{id}/shift/assignment")
    public void assignment(@PathVariable long id, @RequestBody ShiftAssignmentDTO form){
        Optional<Employee> byId = employeeRepository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            EmployeeShiftAssignment assignment = new EmployeeShiftAssignment(form, employee);
            shiftAssignmentRepository.save(assignment);
        }else throw new RuntimeException("This employee is not found");
    }
}
