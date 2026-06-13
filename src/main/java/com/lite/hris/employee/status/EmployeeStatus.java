package com.lite.hris.employee.status;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeJoinDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="employee_status")
@Data
@NoArgsConstructor
public class EmployeeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    public EmployeeStatus(Employee e, EmployeeJoinDTO form) {
        this.employee = e;
        this.status = form.getStatus();
        this.startDate = form.getJoinDate();
        this.endDate = form.getEndDate();
    }
}
