package com.lite.hris.employee;

import com.lite.hris.employee.position.EmployeePosition;
import com.lite.hris.employee.workSite.EmployeeWorkSite;
import com.lite.hris.person.Person;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeProfile {
    private long id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String registrationNo;
    private String currentPosition;
    private String currentDepartment;
    private String currentStatus;
    private String currentOffice;
    private String currentCompany;

    public void setEmployee(Employee employee) {
        this.id = employee.getId();
        this.registrationNo = employee.getEmployeeNo();

        Person p = employee.getPerson();
        this.name = p.getName();
        this.birthDate = p.getBirthDate();
        this.gender = p.getGender();
    }

    public void setPosition(EmployeePosition position) {
        this.currentPosition = position.getPosition().getTitle();
        this.currentDepartment = position.getPosition().getDepartment().getName();
    }

    public void setWorkSite(EmployeeWorkSite site) {
        this.currentOffice = site.getOffice().getName();
        this.currentCompany = site.getOffice().getCompany().getName();
    }
}
