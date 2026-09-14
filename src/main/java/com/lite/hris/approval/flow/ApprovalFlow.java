package com.lite.hris.approval.flow;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "approval_flow")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApprovalFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Embedded
    private Audit audit = new Audit();
}
