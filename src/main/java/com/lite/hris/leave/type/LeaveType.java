package com.lite.hris.leave.type;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leave_type")
@Data
@NoArgsConstructor
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;
    private boolean needAttachment;
    private boolean consumeBalance;
    private int maxDays;
}
