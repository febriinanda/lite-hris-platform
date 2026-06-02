package com.lite.hris.branch;

import com.lite.hris.company.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Branch(BranchDTO form) {
        this.name = form.getName();
        this.address = form.getAddress();
        this.company = form.getCompany();
    }

    public void update(BranchDTO form) {
        this.name = form.getName();
        this.address = form.getAddress();
        this.company = form.getCompany();
    }
}
