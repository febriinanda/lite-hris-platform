package com.lite.hris.employee;

import com.lite.hris.employee.workSite.DeploymentDTO;
import com.lite.hris.employee.workSite.EmployeeWorkSite;
import com.lite.hris.employee.workSite.EmployeeWorkSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDeployService {
    private final EmployeeService employeeService;
    private final EmployeeWorkSiteRepository workSiteRepository;

    public void deploy(long id, DeploymentDTO form){
        Employee employee = employeeService.findById(id);
        EmployeeWorkSite workSite = new EmployeeWorkSite(employee, form);
        workSiteRepository.save(workSite);
    }
}
