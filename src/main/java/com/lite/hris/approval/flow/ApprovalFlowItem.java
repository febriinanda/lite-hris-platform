package com.lite.hris.approval.flow;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "approval_flow_item")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApprovalFlowItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "header_id")
    private ApprovalFlow header;

    private int sequence;

    @Enumerated(EnumType.STRING)
    private FlowType type;

    private String referenceType;
    private long referenceId;

    @Embedded
    private Audit audit = new Audit();
}
