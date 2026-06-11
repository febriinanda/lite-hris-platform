package com.lite.hris.document;

import com.lite.hris.document.personal.PersonalDocument;
import com.lite.hris.document.personal.PersonalDocumentDTO;
import com.lite.hris.document.personal.PersonalDocumentRepository;
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

    @PostMapping("/personal")
    public void personal(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("request") PersonalDocumentDTO form) throws IOException {
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
