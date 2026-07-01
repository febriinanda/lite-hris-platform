package com.lite.hris.employee.leave.grant;

import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="employee_leave_grant")
@NoArgsConstructor
@Data
public class EmployeeLeaveGrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private int grantedDays;
    private int remainingDays;
    private int year;
    private LocalDate earnedDate;
    private LocalDate expireDate;
    private int extensionCount;
    private String status;
}
