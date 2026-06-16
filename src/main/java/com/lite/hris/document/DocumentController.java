package com.lite.hris.document;

import com.lite.hris.FileUpload.FileUploadService;
import com.lite.hris.document.certification.CertificationDocument;
import com.lite.hris.document.certification.CertificationDocumentDTO;
import com.lite.hris.document.certification.CertificationDocumentRepository;
import com.lite.hris.document.educational.EducationalDocument;
import com.lite.hris.document.educational.EducationalDocumentDTO;
import com.lite.hris.document.educational.EducationalDocumentRepository;
import com.lite.hris.document.employment.EmploymentDocument;
import com.lite.hris.document.employment.EmploymentDocumentDTO;
import com.lite.hris.document.employment.EmploymentDocumentRepository;
import com.lite.hris.document.personal.PersonalDocument;
import com.lite.hris.document.personal.PersonalDocumentDTO;
import com.lite.hris.document.personal.PersonalDocumentRepository;
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
    private final PersonalDocumentRepository personalDocumentRepository;
    private final EducationalDocumentRepository educationalDocumentRepository;
    private final EmploymentDocumentRepository employmentDocumentRepository;
    private final CertificationDocumentRepository certificationDocumentRepository;
    private final FileUploadService fileUploadService;

    @PostMapping("/employment")
    public void employment(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid EmploymentDocumentDTO form) throws IOException {
        EmploymentDocument doc = new EmploymentDocument(form);
        fileUploadService.upload(doc, file);
        employmentDocumentRepository.save(doc);
    }
    
    @PostMapping("/certification")
    public void certification(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid CertificationDocumentDTO form) throws IOException {
        CertificationDocument doc = new CertificationDocument(form);
        fileUploadService.upload(doc, file);
        certificationDocumentRepository.save(doc);
    }
    @PostMapping("/educational")
    public void educational(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid EducationalDocumentDTO form) throws IOException {
        EducationalDocument doc = new EducationalDocument(form);
        fileUploadService.upload(doc, file);
        educationalDocumentRepository.save(doc);
    }

    @PostMapping("/personal")
    public void personal(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid PersonalDocumentDTO form) throws IOException {
        PersonalDocument doc = new PersonalDocument(form);
        fileUploadService.upload(doc, file);
        personalDocumentRepository.save(doc);
    }
}
