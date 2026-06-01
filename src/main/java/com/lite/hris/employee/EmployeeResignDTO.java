package com.lite.hris.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResignDTO {
    private LocalDate resignDate;
    private String reason;
}
