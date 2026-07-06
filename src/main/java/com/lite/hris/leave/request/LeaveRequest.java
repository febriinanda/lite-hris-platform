package com.lite.hris.leave.request;

import com.lite.hris.employee.Employee;
import com.lite.hris.leave.type.LeaveType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "leave_request")
@Data
@NoArgsConstructor
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType type;

    private LocalDate startDate;
    private LocalDate endDate;
}
