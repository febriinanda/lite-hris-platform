package com.lite.hris.employee.position;

import com.lite.hris.jobPosition.JobPosition;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssignmentDTO {
    private JobPosition position;
    private LocalDate startDate;
    private LocalDate endDate;
}
