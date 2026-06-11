package com.lite.hris.document.certification;

import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "certification_document")
@NoArgsConstructor
public class CertificationDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Enumerated(EnumType.STRING)
    private DocumentCategory category;

    private String documentNo;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String institution;

    private String fileName;
    private String filePath;
    private long fileSize;
    private LocalDateTime uploadDate;

    public CertificationDocument(CertificationDocumentDTO form) {
        this.person = form.getPerson();
        this.category = form.getCategory();
        this.documentNo = form.getDocumentNo();
        this.issueDate = form.getIssueDate();
        this.expiryDate = form.getExpiryDate();
        this.institution = form.getInstitution();
    }
}
