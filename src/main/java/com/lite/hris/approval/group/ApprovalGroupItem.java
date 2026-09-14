package com.lite.hris.approval.group;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "approval_group_item")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApprovalGroupItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "header_id")
    private ApprovalGroup header;

    @Embedded
    private Audit audit = new Audit();
}
