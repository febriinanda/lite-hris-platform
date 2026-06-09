package com.lite.hris.employee;

import com.lite.hris.employee.position.AssignmentDTO;
import com.lite.hris.employee.position.EmployeePosition;
import com.lite.hris.employee.position.EmployeePositionRepository;
import com.lite.hris.employee.status.EmployeeStatus;
import com.lite.hris.employee.status.EmployeeStatusRepository;
import com.lite.hris.employee.workSite.DeploymentDTO;
import com.lite.hris.employee.workSite.EmployeeWorkSite;
import com.lite.hris.employee.workSite.EmployeeWorkSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeePositionRepository positionRepository;
    private final EmployeeStatusRepository statusRepository;
    private final EmployeeWorkSiteRepository workSiteRepository;
    private final RegistrationNumberValidationService registrationNumberValidationService;

    @GetMapping
    public List<Employee> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("Employee is not found");
    }

    @GetMapping("/{id}/profile")
    public EmployeeProfile profile(@PathVariable long id){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            EmployeeProfile profile = new EmployeeProfile();
            Employee employee = byId.get();

            profile.setEmployee(employee);
            List<EmployeePosition> positions = positionRepository.findByEmployee(employee);
            List<EmployeeStatus> statuses = statusRepository.findByEmployee(employee);
            List<EmployeeWorkSite> sites = workSiteRepository.findByEmployee(employee);
            LocalDate now = LocalDate.now();
            Optional<EmployeePosition> positionOptional = positions.stream().filter(o -> o.getStartDate().isBefore(now) && (o.getEndDate() == null || o.getEndDate().isAfter(now))).max(Comparator.comparing(EmployeePosition::getStartDate));
            if(positionOptional.isPresent()){
                EmployeePosition position = positionOptional.get();
                profile.setPosition(position);
            }

            Optional<EmployeeStatus> statusOptional = statuses.stream().filter(o -> o.getStartDate().isBefore(now) && (o.getEndDate() == null || o.getEndDate().isAfter(now))).max(Comparator.comparing(EmployeeStatus::getStartDate));
            if(statusOptional.isPresent()){
                EmployeeStatus status = statusOptional.get();
                profile.setCurrentStatus(status.getStatus());
            }

            Optional<EmployeeWorkSite> siteOptional = sites.stream().filter(o -> o.getStartDate().isBefore(now) && (o.getEndDate() == null || o.getEndDate().isAfter(now))).max(Comparator.comparing(EmployeeWorkSite::getStartDate));
            if(siteOptional.isPresent()){
                EmployeeWorkSite site = siteOptional.get();
                profile.setWorkSite(site);
            }

            return profile;
        }else throw new RuntimeException("Employee is not found");
    }

    @PostMapping
    public void create(@RequestBody EmployeeJoinDTO form){
        Employee e = new Employee(form);
        repository.save(e);

        EmployeeStatus employeeStatus = new EmployeeStatus(e, form);
        statusRepository.save(employeeStatus);
    }

    @PatchMapping("/{id}/registration/number")
    public void registerNumber(@PathVariable long id, @RequestBody @Validated NumberRegistrationDTO form){
        boolean valid = registrationNumberValidationService.isValid(form.getNumber());
        if(!valid)
            throw new RuntimeException("Rules are not match");

        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            employee.registrationNumber(form);
            repository.save(employee);
        }else throw new RuntimeException("Employee is not found");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody EmployeeResignDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee e = byId.get();
            e.update(form);
            repository.save(e);
        }else throw new RuntimeException("Employee is not found");
    }

    @PostMapping("/{id}/assign")
    public void assign(@PathVariable long id, @RequestBody AssignmentDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            EmployeePosition position = new EmployeePosition(byId.get(), form);
            positionRepository.save(position);
        }else throw new RuntimeException("Employee is not found");
    }

    @PostMapping("/{id}/work/site")
    public void deploy(@PathVariable long id, @RequestBody DeploymentDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            EmployeeWorkSite workSite = new EmployeeWorkSite(byId.get(), form);
            workSiteRepository.save(workSite);
        }else throw new RuntimeException("Employee is not found");
    }
}
