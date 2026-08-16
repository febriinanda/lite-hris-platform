package com.lite.hris.employee;

import com.lite.hris.employee.position.AssignmentDTO;
import com.lite.hris.employee.position.EmployeePosition;
import com.lite.hris.employee.position.EmployeePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeAssignService {
    private final EmployeeService employeeService;
    private final EmployeePositionRepository positionRepository;
    public void assign(long id, AssignmentDTO form){
        Employee employee = employeeService.findById(id);
        EmployeePosition position = new EmployeePosition(employee, form);
        positionRepository.save(position);
    }
}
