package com.lite.hris.shift.pattern;

import com.lite.hris.shift.Shift;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shift_pattern_item")
@Data
@NoArgsConstructor
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
}
