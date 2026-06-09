package com.lite.hris.document.category;

import lombok.Data;

@Data
public class DocumentCategoryDTO {
    private String type;
    private boolean required;
    private boolean multiple;
}
