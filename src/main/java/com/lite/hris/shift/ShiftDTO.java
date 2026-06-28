package com.lite.hris.shift;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftDTO {
    private String code;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int breakDuration;
    private boolean crossDay;
}
