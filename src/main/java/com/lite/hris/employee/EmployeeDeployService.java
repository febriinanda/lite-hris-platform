package com.lite.hris.employee;

import com.lite.hris.employee.workSite.DeploymentDTO;
import com.lite.hris.employee.workSite.EmployeeWorkSite;
import com.lite.hris.employee.workSite.EmployeeWorkSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeDeployService {
    private final EmployeeRepository repository;
    private final EmployeeWorkSiteRepository workSiteRepository;

    public void deploy(long id, DeploymentDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            EmployeeWorkSite workSite = new EmployeeWorkSite(byId.get(), form);
            workSiteRepository.save(workSite);
        }else throw new RuntimeException("Employee is not found");
    }
}
