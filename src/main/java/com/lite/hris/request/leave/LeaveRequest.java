package com.lite.hris.request.leave;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import com.lite.hris.request.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "leave_request")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType type;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Embedded
    private Audit audit = new Audit();

    public LeaveRequest(LeaveRequestForm form) {
        this.employee = form.getRequester();
        this.type = form.getType();
        this.startDate = form.getStartDate();
        this.endDate = form.getEndDate();
    }
}
