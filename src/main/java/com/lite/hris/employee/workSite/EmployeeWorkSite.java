package com.lite.hris.employee.workSite;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import com.lite.hris.office.Office;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee_work_site")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Embedded
    private Audit audit = new Audit();

    public EmployeeWorkSite(Employee employee, DeploymentDTO form) {
        this.employee = employee;
        this.office = form.getOffice();
        this.startDate = form.getStartDate();
        this.endDate = form.getEndDate();
    }
}
