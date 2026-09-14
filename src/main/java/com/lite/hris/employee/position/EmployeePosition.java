package com.lite.hris.employee.position;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import com.lite.hris.jobPosition.JobPosition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "employee_position")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class EmployeePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition position;

    private LocalDate startDate;
    private LocalDate endDate;

    @Embedded
    private Audit audit = new Audit();

    public EmployeePosition(Employee employee, AssignmentDTO form) {
        this.employee = employee;
        this.position = form.getPosition();
        this.startDate = form.getStartDate();
        this.endDate = form.getEndDate();
    }
}
