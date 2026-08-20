package com.lite.hris.approval.task;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import com.lite.hris.request.RequestType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "approval_task")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApprovalTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int sequence;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    private long requestId;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    private int minimumApprovalThisSequence;

    @Embedded
    private Audit audit = new Audit();
}
