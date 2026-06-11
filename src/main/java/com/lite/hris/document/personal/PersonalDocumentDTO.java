package com.lite.hris.document.personal;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalDocumentDTO {
    @NotNull(message = "Person is required")
    private Person person;
    @NotNull(message = "Document should have a category")
    private DocumentCategory category;
    @NotNull(message = "Number should not empty")
    private String number;
    private LocalDate expiryDate;
}
