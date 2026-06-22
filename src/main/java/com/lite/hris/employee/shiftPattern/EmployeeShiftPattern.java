package com.lite.hris.employee.shiftPattern;

import com.lite.hris.employee.Employee;
import com.lite.hris.shift.pattern.ShiftPattern;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name = "employee_shift_pattern")
@Entity
@NoArgsConstructor
public class EmployeeShiftPattern {
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
    private int startSequence;
    private LocalDate endDate;

    public EmployeeShiftPattern(EmployeeShiftPatternDTO form, Employee e) {
        this.employee = e;
        this.pattern = form.getPattern();
        this.startSequence = form.getStartSequence();
        this.effectiveDate = form.getEffectiveDate();
    }
}
