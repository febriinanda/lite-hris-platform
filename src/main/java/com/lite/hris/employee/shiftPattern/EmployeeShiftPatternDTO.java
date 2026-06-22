package com.lite.hris.employee.shiftPattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.shift.pattern.ShiftPattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeShiftPatternDTO {
    private ShiftPattern pattern;
    private LocalDate effectiveDate;
    private int startSequence;
}
