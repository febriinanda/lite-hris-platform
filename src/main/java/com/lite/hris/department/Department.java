package com.lite.hris.department;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private boolean deleted;

    @Embedded
    private Audit audit = new Audit();

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
