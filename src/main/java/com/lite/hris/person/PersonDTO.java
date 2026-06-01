package com.lite.hris.person;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDTO {
    private String name;
    private String gender;
    private LocalDate birthDate;
}
