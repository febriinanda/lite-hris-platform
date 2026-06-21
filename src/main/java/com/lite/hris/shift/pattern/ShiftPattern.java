package com.lite.hris.shift.pattern;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shift_pattern")
@NoArgsConstructor
@Data
public class ShiftPattern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;
    private int cycleLength;
    private String description;
    private boolean deleted;
}
