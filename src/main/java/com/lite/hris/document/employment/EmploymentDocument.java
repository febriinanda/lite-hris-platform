package com.lite.hris.document.employment;

import com.lite.hris.FileUpload.HasFileUpload;
import com.lite.hris.config.Audit;
import com.lite.hris.document.DocumentCategory;
import com.lite.hris.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employment_document")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EmploymentDocument implements HasFileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private DocumentCategory category;

    private String documentNo;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    private String fileName;
    private String filePath;
    private long fileSize;
    private LocalDateTime uploadDate;

    @Embedded
    private Audit audit = new Audit();

    public EmploymentDocument(EmploymentDocumentDTO form) {
        this.employee = form.getEmployee();
        this.category = form.getCategory();
        this.documentNo = form.getDocumentNo();
        this.issueDate = form.getIssueDate();
        this.expiryDate = form.getExpiryDate();
    }
}
