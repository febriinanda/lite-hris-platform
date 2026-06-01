package com.lite.hris.employee;

import com.lite.hris.person.Person;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeJoinDTO {
    private Person person;
    private LocalDate joinDate;
}
