package com.lite.hris.FileUpload;

import java.time.LocalDateTime;

public interface HasFileUpload {
    void setFileName(String s);
    void setFilePath(String p);
    void setFileSize(long s);
    void setUploadDate(LocalDateTime date);
}
