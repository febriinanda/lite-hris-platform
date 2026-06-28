package com.lite.hris.shift.pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.shift.Shift;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftPatternItemDTO {
    private int sequence;
    private Shift shift;
}
