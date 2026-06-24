package com.lite.hris.employee.shiftAssignment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.shift.pattern.ShiftPattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftAssignmentDTO {
    private ShiftPattern pattern;
    private LocalDate effectiveDate;
}
