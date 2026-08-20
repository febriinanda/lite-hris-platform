package com.lite.hris.approval.group;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "approval_group")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApprovalGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private ApprovalMode mode;
    private int minimumApproval;

    @Embedded
    private Audit audit = new Audit();
}
