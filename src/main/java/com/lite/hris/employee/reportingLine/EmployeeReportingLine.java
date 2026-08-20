package com.lite.hris.employee.reportingLine;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "employee_reporting_line")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EmployeeReportingLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    private LocalDate effectiveFrom;
    private LocalDate effectiveUntil;

    @Embedded
    private Audit audit = new Audit();
}
