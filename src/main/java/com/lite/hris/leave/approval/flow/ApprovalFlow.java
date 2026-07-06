package com.lite.hris.leave.approval.flow;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "approval_flow")
@Data
@NoArgsConstructor
public class ApprovalFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
