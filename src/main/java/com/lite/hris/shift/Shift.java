package com.lite.hris.shift;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@Entity
@Table(name = "shift")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int breakDuration;
    private boolean crossDay;
    private boolean deleted;

    @Embedded
    private Audit audit = new Audit();

    public Shift(ShiftDTO form) {
        this.code = form.getCode();
        this.name = form.getName();
        this.startTime = form.getStartTime();
        this.endTime = form.getEndTime();
        this.breakDuration = form.getBreakDuration();
        this.crossDay = form.isCrossDay();
    }

    public void update(ShiftDTO form) {
        this.code = form.getCode();
        this.name = form.getName();
        this.startTime = form.getStartTime();
        this.endTime = form.getEndTime();
        this.breakDuration = form.getBreakDuration();
        this.crossDay = form.isCrossDay();
    }

    public void deleted() {
        this.deleted = true;
    }
}
