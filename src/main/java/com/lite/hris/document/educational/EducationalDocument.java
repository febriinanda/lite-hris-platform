package com.lite.hris.document.educational;

import com.lite.hris.FileUpload.HasFileUpload;
import com.lite.hris.config.Audit;
import com.lite.hris.document.DocumentCategory;
import com.lite.hris.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "educational_document")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EducationalDocument implements HasFileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Enumerated(EnumType.STRING)
    private DocumentCategory category;

    private String educationLevel;
    private String institution;
    private String major;
    private int initialYear;
    private int graduationYear;
    private String documentNo;
    private String fileName;
    private String filePath;
    private long fileSize;
    private LocalDateTime uploadDate;
    private LocalDate expiryDate;

    @Embedded
    private Audit audit = new Audit();

    public EducationalDocument(EducationalDocumentDTO form) {
        this.person = form.getPerson();
        this.category = form.getCategory();
        this.educationLevel = form.getEducationLevel();
        this.institution = form.getInstitution();
        this.major = form.getMajor();
        this.initialYear = form.getInitialYear();
        this.graduationYear = form.getGraduationYear();
        this.documentNo = form.getDocumentNo();
        this.expiryDate = form.getExpiryDate();

    }
}
