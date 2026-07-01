package com.lite.hris.employee.leave.transaction;

import com.lite.hris.employee.leave.grant.EmployeeLeaveGrant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employee_leave_transaction")
@NoArgsConstructor
public class EmployeeLeaveTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "grant_id")
    private EmployeeLeaveGrant grant;

    private String transactionType;
    private String referenceType;
    private long referenceId;
    private int amount;
    private String remarks;
    private String createdBy;
    private String createdAt;
}
