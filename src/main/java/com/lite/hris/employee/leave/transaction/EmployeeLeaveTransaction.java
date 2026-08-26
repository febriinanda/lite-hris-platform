package com.lite.hris.employee.leave.transaction;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Table(name = "employee_leave_transaction")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EmployeeLeaveTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "grant_id")
    private EmployeeLeaveGrant grant;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private LeaveTransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private LeaveReferenceType referenceType;
    private long referenceId;
    private int amount;
    private String remarks;

    @Embedded
    private Audit audit = new Audit();
}
