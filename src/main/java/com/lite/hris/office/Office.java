package com.lite.hris.office;

import com.lite.hris.company.Company;
import com.lite.hris.config.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "office")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private OfficeType type;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Embedded
    private Audit audit = new Audit();

    public Office(OfficeDTO form) {
        this.name = form.getName();
        this.address = form.getAddress();
        this.type = form.getType();
        this.company = form.getCompany();
    }

    public void update(OfficeDTO form) {
        this.name = form.getName();
        this.address = form.getAddress();
        this.type = form.getType();
        this.company = form.getCompany();
    }

    public void delete() {
        this.deleted = true;
    }
}
