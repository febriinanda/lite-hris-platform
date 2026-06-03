package com.lite.hris.employee.workSite;

import com.lite.hris.office.Office;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeploymentDTO {
    public Office office;
    public LocalDate startDate;
    public LocalDate endDate;
}
