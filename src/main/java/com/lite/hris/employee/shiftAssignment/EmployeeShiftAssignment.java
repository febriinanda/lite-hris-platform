package com.lite.hris.employee.shiftAssignment;

import com.lite.hris.employee.Employee;
import com.lite.hris.shift.pattern.ShiftPattern;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee_shift_assignment")
@NoArgsConstructor
public class EmployeeShiftAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "shift_pattern_id")
    private ShiftPattern pattern;

    private LocalDate effectiveDate;

    public EmployeeShiftAssignment(ShiftAssignmentDTO form, Employee employee) {
        this.employee = employee;
        this.pattern = form.getPattern();
        this.effectiveDate = form.getEffectiveDate();
    }
}
