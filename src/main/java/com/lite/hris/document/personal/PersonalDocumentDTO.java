package com.lite.hris.document.personal;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalDocumentDTO {
    private Person person;
    private DocumentCategory category;
    private String number;
    private LocalDate expiryDate;
}
