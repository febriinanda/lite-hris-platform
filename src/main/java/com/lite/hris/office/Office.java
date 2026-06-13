package com.lite.hris.office;

import com.lite.hris.company.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "office")
@Data
@NoArgsConstructor
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
}
