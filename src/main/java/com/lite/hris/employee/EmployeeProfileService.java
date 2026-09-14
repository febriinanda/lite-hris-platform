package com.lite.hris.employee;

import com.lite.hris.employee.position.EmployeePosition;
import com.lite.hris.employee.position.EmployeePositionRepository;
import com.lite.hris.employee.status.EmployeeStatus;
import com.lite.hris.employee.status.EmployeeStatusRepository;
import com.lite.hris.employee.workSite.EmployeeWorkSite;
import com.lite.hris.employee.workSite.EmployeeWorkSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    private final EmployeeService employeeService;
    private final EmployeePositionRepository positionRepository;
    private final EmployeeStatusRepository statusRepository;
    private final EmployeeWorkSiteRepository workSiteRepository;

    public EmployeeProfile getProfile(long id){
        Employee employee = employeeService.findById(id);
        EmployeeProfile profile = new EmployeeProfile();

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
    }
}
