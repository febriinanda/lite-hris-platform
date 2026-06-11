package com.lite.hris.document.educational;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationalDocumentDTO {
    @NotNull(message = "Person is required")
    private Person person;

    @NotNull(message = "Document should have a category")
    private DocumentCategory category;

    private String educationLevel;
    private String institution;
    private String major;
    private int initialYear;
    private int graduationYear;
    private String documentNo;
    private LocalDate expiryDate;
}
