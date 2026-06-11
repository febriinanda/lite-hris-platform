package com.lite.hris.document.certification;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificationDocumentDTO {
    @NotNull(message = "Person is required")
    private Person person;

    @NotNull(message = "Document category is a must")
    private DocumentCategory category;

    @NotNull(message = "Document number is required")
    private String documentNo;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    @NotNull(message = "Institution name should not empty")
    private String institution;
}
