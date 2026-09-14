package com.lite.hris.document.educational;

import com.lite.hris.fileUpload.FileUploadService;
import com.lite.hris.document.DocumentCategory;
import com.lite.hris.exception.DocumentCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EducationalDocumentService {
    private final FileUploadService fileUploadService;
    private final EducationalDocumentRepository educationalDocumentRepository;
    public void submit(MultipartFile file, EducationalDocumentDTO form) throws IOException {
        DocumentCategory category = form.getCategory();

        if(!category.getGroup().equals("educational"))
            throw new DocumentCategoryException("This category is not allowed in this endpoint");

        EducationalDocument doc = new EducationalDocument(form);
        fileUploadService.upload(doc, file);
        educationalDocumentRepository.save(doc);
    }
}
