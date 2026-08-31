package com.lite.hris.fileUpload;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileUploadService {
    public void upload(HasFileUpload doc, MultipartFile file) throws IOException {
        handle(doc,file,"storage/documents");
    }

    private void handle(HasFileUpload doc, MultipartFile file, String uploadDir) throws IOException {
        if(file != null && !file.isEmpty()){
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            String newFileName = fileName+"."+ext;
            Path documentStorage = Paths.get(uploadDir);
            Files.createDirectories(documentStorage);
            Path path = documentStorage.resolve(newFileName);
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);

            doc.setFileName(newFileName);
            doc.setFilePath(path.toString());
            doc.setFileSize(file.getSize());
            doc.setUploadDate(LocalDateTime.now());
        }
    }

    @Transactional
    public void photoProfile(HasFileUpload p, MultipartFile file) throws IOException {
        handle(p, file, "storage/photo-profile");
    }
}
