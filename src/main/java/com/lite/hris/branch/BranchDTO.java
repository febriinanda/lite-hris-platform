package com.lite.hris.branch;

import com.lite.hris.company.Company;
import lombok.Data;

@Data
public class BranchDTO {
    private Company company;
    private String name;
    private String address;
}
