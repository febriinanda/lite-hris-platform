package com.lite.hris.person;

import com.lite.hris.FileUpload.HasFileUpload;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
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
}
