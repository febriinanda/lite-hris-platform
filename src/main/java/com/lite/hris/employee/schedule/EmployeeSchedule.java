package com.lite.hris.employee.schedule;

import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_schedule")
@Data
@NoArgsConstructor
public class EmployeeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate scheduleDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int breakDuration;
    private String code;
    private String source;
    private boolean off;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
