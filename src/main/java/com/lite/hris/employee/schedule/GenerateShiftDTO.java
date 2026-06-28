package com.lite.hris.employee.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateShiftDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
