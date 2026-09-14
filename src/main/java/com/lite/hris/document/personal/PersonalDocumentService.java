package com.lite.hris.document.personal;

import com.lite.hris.fileUpload.FileUploadService;
import com.lite.hris.document.DocumentCategory;
import com.lite.hris.exception.DocumentCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PersonalDocumentService {
    private final PersonalDocumentRepository personalDocumentRepository;
    private final FileUploadService fileUploadService;
    public void submit(MultipartFile file, PersonalDocumentDTO form) throws IOException {
        DocumentCategory category = form.getCategory();
        if(!category.getGroup().equals("personal"))
            throw new DocumentCategoryException("This category is not allowed in this endpoint");

        PersonalDocument doc = new PersonalDocument(form);
        fileUploadService.upload(doc, file);
        personalDocumentRepository.save(doc);
    }
}
