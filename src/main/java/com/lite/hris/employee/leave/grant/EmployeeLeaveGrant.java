package com.lite.hris.employee.leave.grant;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name="employee_leave_grant")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
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

    @Enumerated(EnumType.STRING)
    private LeaveGrantStatus status;

    @Embedded
    private Audit audit = new Audit();

    public EmployeeLeaveGrant(LeaveBalanceGrantRequest form) {
        this.employee = form.getEmployee();
        this.grantedDays = form.getGrantedDays();
        this.remainingDays = form.getGrantedDays();
        this.year = form.getYear();
        this.earnedDate = form.getEarnedDate();
        this.expireDate = form.getExpireDate();
        this.status = LeaveGrantStatus.ACTIVE;
    }

    public LocalDate getFinalExpireDate() {
        return this.expireDate.plusMonths(this.getExtensionCount() * 3L);
    }
}
