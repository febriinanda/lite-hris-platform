package com.lite.hris.employee;

import com.lite.hris.employee.position.AssignmentDTO;
import com.lite.hris.employee.position.EmployeePosition;
import com.lite.hris.employee.position.EmployeePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeAssignService {
    private final EmployeeRepository repository;
    private final EmployeePositionRepository positionRepository;
    public void assign(long id, AssignmentDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            EmployeePosition position = new EmployeePosition(byId.get(), form);
            positionRepository.save(position);
        }else throw new RuntimeException("Employee is not found");
    }
}
