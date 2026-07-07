package com.lite.hris.leave.approval.group;

import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "approval_group_item")
@Data
@NoArgsConstructor
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
}
