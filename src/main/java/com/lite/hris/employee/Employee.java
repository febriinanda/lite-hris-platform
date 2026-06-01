package com.lite.hris.employee;

import com.lite.hris.person.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String employeeNo;
    private LocalDate joinDate;
    private LocalDate resignDate;
}
