package com.lite.hris.employee;

import com.lite.hris.employee.position.AssignmentDTO;
import com.lite.hris.employee.workSite.DeploymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeProfileService profileService;
    private final EmployeeRegistrationService registrationService;
    private final EmployeeResignService resignService;
    private final EmployeeAssignService assignService;
    private final EmployeeDeployService deployService;

    @GetMapping
    public List<Employee> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
        return service.findById(id);
    }

    @GetMapping("/{id}/profile")
    public EmployeeProfile profile(@PathVariable long id){
        return profileService.getProfile(id);
    }

    @PostMapping
    public void create(@RequestBody EmployeeJoinDTO form){
        registrationService.register(form);
    }

    @PatchMapping("/{id}/registration/number")
    public void registerNumber(@PathVariable long id, @RequestBody @Validated NumberRegistrationDTO form){
        registrationService.registerNumber(id, form);
    }

    @PutMapping("/{id}/resign")
    public void resign(@PathVariable long id, @RequestBody EmployeeResignDTO form){
        resignService.resign(id, form);
    }

    @PostMapping("/{id}/assign")
    public void assign(@PathVariable long id, @RequestBody AssignmentDTO form){
        assignService.assign(id, form);
    }

    @PostMapping("/{id}/work/site")
    public void deploy(@PathVariable long id, @RequestBody DeploymentDTO form){
        deployService.deploy(id, form);
    }
}
