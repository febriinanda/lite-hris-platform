package com.lite.hris.employee;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class NumberRegistrationDTO {
    @NonNull
    private String number;
}
