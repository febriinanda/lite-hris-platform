package com.lite.hris.FileUpload;

import org.springframework.stereotype.Service;
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
    }

    public void photoProfile(HasFileUpload p, MultipartFile file) throws IOException {
        if(file != null && !file.isEmpty()){
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            String newFileName = fileName+"."+ext;
            Path documentStorage = Paths.get("storage/photo-profile");
            Files.createDirectories(documentStorage);
            Path path = documentStorage.resolve(newFileName);
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);

            p.setFileName(newFileName);
            p.setFilePath(path.toString());
            p.setFileSize(file.getSize());
            p.setUploadDate(LocalDateTime.now());
        }
    }
}
