package com.lite.hris.person;

import com.lite.hris.fileUpload.HasFileUpload;
import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Person implements HasFileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private LocalDate birthDate;
    private String gender;
    private boolean deleted;

    private String fileName;
    private String filePath;
    private long fileSize;
    private LocalDateTime uploadDate;

    @Embedded
    private Audit audit = new Audit();

    public Person(PersonDTO form) {
        this.name = form.getName();
        this.gender = form.getGender();
        this.birthDate = form.getBirthDate();
    }

    public void update(PersonDTO form) {
        this.name = form.getName();
        this.gender = form.getGender();
        this.birthDate = form.getBirthDate();
    }

    public void delete() {
        this.deleted = true;
    }
}
