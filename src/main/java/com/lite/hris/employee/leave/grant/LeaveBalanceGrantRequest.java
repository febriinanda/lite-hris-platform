package com.lite.hris.employee.leave.grant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.employee.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaveBalanceGrantRequest {
    private Employee employee;
    private int grantedDays;
    private int year;

    public LocalDate getEarnedDate() {
        return LocalDate.of(this.year,1,1);
    }

    public LocalDate getExpireDate(){
        return LocalDate.of(this.year, 12,31);
    }
}
