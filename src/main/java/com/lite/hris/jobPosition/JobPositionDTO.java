package com.lite.hris.jobPosition;

import com.lite.hris.department.Department;
import lombok.Data;

@Data
public class JobPositionDTO {
    private Department department;
    private String title;
}
