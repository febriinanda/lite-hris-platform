package com.lite.hris.employee.schedule;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import com.lite.hris.shift.Shift;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_schedule")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Embedded
    private Audit audit = new Audit();

    public void setup(Shift shift) {
        this.startDate = this.scheduleDate.atTime(shift.getStartTime());
        this.endDate = shift.isCrossDay()?this.scheduleDate.atTime(shift.getEndTime()).plusDays(1):this.scheduleDate.atTime(shift.getEndTime());
        this.breakDuration = shift.getBreakDuration();
        this.off = false;
        this.code = shift.getCode();
    }
}
