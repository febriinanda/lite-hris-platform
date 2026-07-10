package com.lite.hris.approval.resolver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lite.hris.employee.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties
public class ApprovalResolved {
    private int minimumApproval;
    private List<Employee> employees = new ArrayList<>();
}
