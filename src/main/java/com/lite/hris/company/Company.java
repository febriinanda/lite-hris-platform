package com.lite.hris.company;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private boolean deleted;

    public Company(CompanyDTO form) {
        this.name = form.getName();
    }

    public void update(CompanyDTO form) {
        this.name = form.getName();
    }
}
