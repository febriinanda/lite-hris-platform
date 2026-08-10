package com.lite.hris.employee.reportingLine;

import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employee_reporting_line")
@Data
@NoArgsConstructor
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
}
