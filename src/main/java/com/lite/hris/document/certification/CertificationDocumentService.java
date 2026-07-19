package com.lite.hris.document.certification;

import com.lite.hris.FileUpload.FileUploadService;
import com.lite.hris.document.DocumentCategory;
import com.lite.hris.exception.DocumentCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CertificationDocumentService {
    private final FileUploadService fileUploadService;
    private final CertificationDocumentRepository certificationDocumentRepository;
    public void submit(MultipartFile file, CertificationDocumentDTO form) throws IOException {
        DocumentCategory category = form.getCategory();
        if(!category.getGroup().equals("certification"))
            throw new DocumentCategoryException("This category is not allowed in this endpoint");

        CertificationDocument doc = new CertificationDocument(form);
        fileUploadService.upload(doc, file);
        certificationDocumentRepository.save(doc);
    }
}
