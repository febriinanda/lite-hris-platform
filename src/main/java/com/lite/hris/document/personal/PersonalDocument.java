package com.lite.hris.document.personal;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "personal_document")
@Data
@NoArgsConstructor
public class PersonalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Enumerated(EnumType.STRING)
    private DocumentCategory category;

    private String documentNo;
    private String fileName;
    private String filePath;
    private long fileSize;
    private LocalDateTime uploadDate;
    private LocalDate expiryDate;

    public PersonalDocument(PersonalDocumentDTO form) {
        this.person = form.getPerson();
        this.category = form.getCategory();
        this.documentNo = form.getNumber();
        this.expiryDate = form.getExpiryDate();
    }
}
