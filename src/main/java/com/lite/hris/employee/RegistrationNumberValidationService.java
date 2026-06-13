package com.lite.hris.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationNumberValidationService {
    private final Pattern registrationNumberPattern;

    public RegistrationNumberValidationService(@Value("${registration.number.regex}") String regex) {
        this.registrationNumberPattern = Pattern.compile(regex);
    }

    public boolean isValid(String value){
        return registrationNumberPattern.matcher(value).matches();
    }
}
