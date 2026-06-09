package com.lite.hris.document.category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_category")
@Data
@NoArgsConstructor
public class DocumentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private boolean required;
    private boolean multiple;
    private boolean deleted;

    public DocumentCategory(DocumentCategoryDTO form) {
        this.type = form.getType();
        this.required = form.isRequired();
        this.multiple = form.isMultiple();
    }

    public void update(DocumentCategoryDTO form) {
        this.type = form.getType();
        this.required = form.isRequired();
        this.multiple = form.isMultiple();
    }
}
