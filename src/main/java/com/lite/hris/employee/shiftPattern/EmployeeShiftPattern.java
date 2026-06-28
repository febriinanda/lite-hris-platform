package com.lite.hris.employee.shiftPattern;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import com.lite.hris.shift.pattern.ShiftPattern;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public boolean eligible(LocalDate startDate, LocalDate endDate) {
        boolean s = this.effectiveDate.isAfter(startDate) && this.effectiveDate.isBefore(endDate);
        boolean e = this.endDate == null || (this.endDate.isAfter(startDate) || this.endDate.isBefore(endDate));
        return s||e;
    }

    public List<EmployeeSchedule> generateEmptySchedules(Employee employee, LocalDate startDate, LocalDate endDate) {
        LocalDate refDate = this.effectiveDate.isAfter(startDate)?this.effectiveDate:startDate;
        LocalDate realEndDate = this.endDate == null || this.endDate.isAfter(endDate) ? endDate : this.endDate;
        List<EmployeeSchedule> empties = new ArrayList<>();
        while (refDate.isBefore(realEndDate)){
            EmployeeSchedule s = new EmployeeSchedule();
            s.setScheduleDate(refDate);
            s.setEmployee(employee);
            s.setSource("PATTERN");
            empties.add(s);
            refDate = refDate.plusDays(1);
        }

        return empties;
    }
}
