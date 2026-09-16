package com.lite.hris.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalContactInformationService {
    private final PersonService personService;
    @Transactional
    public void update(long pid, PersonalContactInformation contact){
        Person p = personService.findById(pid);
        p.update(contact);
    }
}
