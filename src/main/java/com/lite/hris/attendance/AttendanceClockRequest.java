package com.lite.hris.attendance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.employee.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AttendanceClockRequest {
    private Employee employee;
    private LocalDateTime clock;
}
