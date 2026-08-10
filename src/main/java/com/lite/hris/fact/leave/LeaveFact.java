package com.lite.hris.fact.leave;

import com.lite.hris.employee.Employee;
import com.lite.hris.request.leave.LeaveRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "leave_fact")
@Data
@NoArgsConstructor
public class LeaveFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate attendanceDate;
    private String code;
    private boolean consumeBalance;

    @ManyToOne
    @JoinColumn(name = "leave_request_id")
    private LeaveRequest reference;
}
