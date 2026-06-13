package com.lite.hris.employee.workSite;

import com.lite.hris.employee.Employee;
import com.lite.hris.office.Office;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee_work_site")
@NoArgsConstructor
public class EmployeeWorkSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    private LocalDate startDate;
    private LocalDate endDate;

    public EmployeeWorkSite(Employee employee, DeploymentDTO form) {
        this.employee = employee;
        this.office = form.getOffice();
        this.startDate = form.getStartDate();
        this.endDate = form.getEndDate();
    }
}
