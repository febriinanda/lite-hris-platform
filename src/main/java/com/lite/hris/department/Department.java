package com.lite.hris.department;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private boolean deleted;

    public Department(DepartmentDTO form) {
        this.name = form.getName();
    }

    public void update(DepartmentDTO form) {
        this.name = form.getName();
    }

    public void delete() {
        this.deleted = false;
    }
}
