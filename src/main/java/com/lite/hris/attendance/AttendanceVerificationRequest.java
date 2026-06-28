package com.lite.hris.attendance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.employee.Employee;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceVerificationRequest {
    private Employee verifiedBy;
    private String note;
}
