package com.lite.hris.document.employment;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.employee.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmploymentDocumentDTO {
    @NotNull(message = "Employee is required")
    private Employee employee;

    @NotNull(message = "Document category is a must")
    private DocumentCategory category;

    @NotNull(message = "Document number is required")
    private String documentNo;

    @NotNull(message = "Document number is required")
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
