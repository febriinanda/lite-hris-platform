package com.lite.hris.company;

import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private boolean deleted;

    @Embedded
    private Audit audit = new Audit();

    public Company(CompanyDTO form) {
        this.name = form.getName();
    }

    public void update(CompanyDTO form) {
        this.name = form.getName();
    }

    public void delete() {
        this.deleted = true;
    }
}
