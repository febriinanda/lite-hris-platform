package com.lite.hris.attendance;

import com.lite.hris.config.Audit;
import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "attendance_log")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AttendanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime time;

    @Embedded
    private Audit audit = new Audit();

    public AttendanceLog(AttendanceClockRequest form) {
        this.employee = form.getEmployee();
        this.time = form.getClock();
    }
}
