package com.lite.hris.shift.pattern;

import com.lite.hris.config.Audit;
import com.lite.hris.shift.Shift;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "shift_pattern_item")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ShiftPatternItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pattern_id")
    private ShiftPattern pattern;

    private int sequence;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @Embedded
    private Audit audit = new Audit();

    public ShiftPatternItem(ShiftPatternItemDTO o, ShiftPattern sp) {
        this.pattern = sp;
        this.sequence = o.getSequence();
        this.shift = o.getShift();
    }
}
