package com.lite.hris.shift.pattern;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "shift_pattern")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class ShiftPattern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;
    private int cycleLength;
    private String description;
    private boolean deleted;

    @Embedded
    private Audit audit = new Audit();

    public ShiftPattern(ShiftPatternDTO form) {
        this.code = form.getCode();
        this.name = form.getName();
        this.cycleLength = form.getCycleLength();
        this.description = form.getDescription();
    }
}
