package com.lite.hris.document;

import com.lite.hris.document.certification.CertificationDocumentDTO;
import com.lite.hris.document.certification.CertificationDocumentService;
import com.lite.hris.document.educational.EducationalDocumentDTO;
import com.lite.hris.document.educational.EducationalDocumentService;
import com.lite.hris.document.employment.EmploymentDocumentDTO;
import com.lite.hris.document.employment.EmploymentDocumentService;
import com.lite.hris.document.personal.PersonalDocumentDTO;
import com.lite.hris.document.personal.PersonalDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final PersonalDocumentService personalDocumentService;
    private final CertificationDocumentService certificationDocumentService;
    private final EmploymentDocumentService employmentDocumentService;
    private final EducationalDocumentService educationalDocumentService;

    @PostMapping("/employment")
    public void employment(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid EmploymentDocumentDTO form) throws IOException {
        employmentDocumentService.submit(file, form);
    }
    
    @PostMapping("/certification")
    public void certification(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid CertificationDocumentDTO form) throws IOException {
        certificationDocumentService.submit(file, form);
    }
    @PostMapping("/educational")
    public void educational(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid EducationalDocumentDTO form) throws IOException {
        educationalDocumentService.submit(file, form);
    }

    @PostMapping("/personal")
    public void personal(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid PersonalDocumentDTO form) throws IOException {
        personalDocumentService.submit(file, form);
    }
}
