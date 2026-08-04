package com.lite.hris.person;

import com.lite.hris.FileUpload.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoProfileUploaderService {
    private final PersonService personService;
    private final FileUploadService fileUploadService;

    public void upload(long id, MultipartFile file) throws IOException {
        Person p = personService.findById(id);
        fileUploadService.photoProfile(p, file);
    }
}
