package com.lite.hris.request.leave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.employee.Employee;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaveRequestForm {
    private Employee requester;
    private LeaveType type;
    private LocalDate startDate;
    private LocalDate endDate;
}
