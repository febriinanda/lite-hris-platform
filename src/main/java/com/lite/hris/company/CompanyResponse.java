package com.lite.hris.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private long id;
    private String name;

    public static CompanyResponse from(Company company){
        return new CompanyResponse(company.getId(), company.getName());
    }
}
