package com.lite.hris.document;

import com.lite.hris.document.personal.PersonalDocument;
import com.lite.hris.document.personal.PersonalDocumentDTO;
import com.lite.hris.document.personal.PersonalDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final PersonalDocumentRepository personalDocumentRepository;

    @PostMapping("/personal")
    public void personal(@RequestBody PersonalDocumentDTO form){
        PersonalDocument doc = new PersonalDocument(form);
        personalDocumentRepository.save(doc);
    }
}
