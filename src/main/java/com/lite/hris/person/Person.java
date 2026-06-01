package com.lite.hris.person;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private LocalDate birthDate;
    private String gender;
    private boolean deleted;

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
