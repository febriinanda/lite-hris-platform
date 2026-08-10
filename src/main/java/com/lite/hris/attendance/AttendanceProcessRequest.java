package com.lite.hris.attendance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AttendanceProcessRequest {
    private LocalDate scheduleDate;
}
