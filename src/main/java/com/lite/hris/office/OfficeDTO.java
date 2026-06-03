package com.lite.hris.office;

import com.lite.hris.company.Company;
import lombok.Data;

@Data
public class OfficeDTO {
    private Company company;
    private String name;
    private String address;
    private OfficeType type;
}
