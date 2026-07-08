package com.lite.hris.approval.group;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "approval_group")
@Data
@NoArgsConstructor
public class ApprovalGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private ApprovalMode mode;
    private int minimumApproval;
}
