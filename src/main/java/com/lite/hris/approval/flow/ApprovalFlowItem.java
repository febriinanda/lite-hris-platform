package com.lite.hris.approval.flow;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "approval_flow_item")
@Data
@NoArgsConstructor
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
}
