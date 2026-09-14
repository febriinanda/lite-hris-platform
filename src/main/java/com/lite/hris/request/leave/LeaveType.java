package com.lite.hris.request.leave;

import com.lite.hris.approval.flow.ApprovalFlow;
import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "leave_type")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;
    private boolean needAttachment;
    private boolean consumeBalance;
    private int maxDays;

    @ManyToOne
    @JoinColumn(name = "approval_flow_id")
    private ApprovalFlow approvalFlow;

    @Embedded
    private Audit audit = new Audit();
}
