package com.lite.hris.document;

import com.lite.hris.document.personal.PersonalDocument;
import com.lite.hris.document.personal.PersonalDocumentDTO;
import com.lite.hris.document.personal.PersonalDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final PersonalDocumentRepository personalDocumentRepository;

    @PostMapping("/personal")
    public void personal(@RequestPart("file") MultipartFile file, @RequestPart("request") PersonalDocumentDTO form){
        PersonalDocument doc = new PersonalDocument(form);
        personalDocumentRepository.save(doc);
    }
}
