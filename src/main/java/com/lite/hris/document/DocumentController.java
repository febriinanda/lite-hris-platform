package com.lite.hris.document;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final PersonalDocumentRepository personalDocumentRepository;
    private final EducationalDocumentRepository educationalDocumentRepository;
    private final EmploymentDocumentRepository employmentDocumentRepository;

    @PostMapping("/employment")
    public void employment(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid EmploymentDocumentDTO form) throws IOException {
        EmploymentDocument doc = new EmploymentDocument(form);
        if(file != null && !file.isEmpty()){
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            String newFileName = fileName+"."+ext;
            Path documentStorage = Paths.get("storage/documents");
            Files.createDirectories(documentStorage);
            Path path = documentStorage.resolve(newFileName);
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);

            doc.setFileName(newFileName);
            doc.setFilePath(path.toString());
            doc.setFileSize(file.getSize());
            doc.setUploadDate(LocalDateTime.now());
        }
        employmentDocumentRepository.save(doc);
    }

    @PostMapping("/educational")
    public void educational(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid EducationalDocumentDTO form) throws IOException {
        EducationalDocument doc = new EducationalDocument(form);
        if(file != null && !file.isEmpty()){
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            String newFileName = fileName+"."+ext;
            Path documentStorage = Paths.get("storage/documents");
            Files.createDirectories(documentStorage);
            Path path = documentStorage.resolve(newFileName);
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);

            doc.setFileName(newFileName);
            doc.setFilePath(path.toString());
            doc.setFileSize(file.getSize());
            doc.setUploadDate(LocalDateTime.now());
        }
        educationalDocumentRepository.save(doc);
    }

    @PostMapping("/personal")
    public void personal(@RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("request") @Valid PersonalDocumentDTO form) throws IOException {
        PersonalDocument doc = new PersonalDocument(form);
        if(file != null && !file.isEmpty()){
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            String newFileName = fileName+"."+ext;
            Path documentStorage = Paths.get("storage/documents");
            Files.createDirectories(documentStorage);
            Path path = documentStorage.resolve(newFileName);
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);

            doc.setFileName(newFileName);
            doc.setFilePath(path.toString());
            doc.setFileSize(file.getSize());
            doc.setUploadDate(LocalDateTime.now());
        }
        personalDocumentRepository.save(doc);
    }
}
