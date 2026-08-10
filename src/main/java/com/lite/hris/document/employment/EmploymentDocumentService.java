package com.lite.hris.document.employment;

import com.lite.hris.FileUpload.FileUploadService;
import com.lite.hris.document.DocumentCategory;
import com.lite.hris.exception.DocumentCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmploymentDocumentService {
    private final FileUploadService fileUploadService;
    private final EmploymentDocumentRepository employmentDocumentRepository;
    public void submit(MultipartFile file, EmploymentDocumentDTO form) throws IOException {
        DocumentCategory category = form.getCategory();
        if(!category.getGroup().equals("employment"))
            throw new DocumentCategoryException("This category is not allowed in this endpoint");

        EmploymentDocument doc = new EmploymentDocument(form);
        fileUploadService.upload(doc, file);
        employmentDocumentRepository.save(doc);
    }
}
