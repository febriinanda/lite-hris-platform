package com.lite.hris.document;

import lombok.Getter;

@Getter
public enum DocumentCategory {
    CITIZENSHIP("KTP", true, false),
    TAX("NPWP",false, false),
    FAMILY_REGISTRATION("KK", true, false),
    CONTRACT_SIGNED("Contract Signed", true, true),
    FORMAL_EDUCATION("Formal Education", true, true),
    INFORMAL_EDUCATION("Informal Education", false, true),
    CERTIFICATION("Certification", false, true);

    private final String name;
    private final boolean required;
    private final boolean multiples;

    DocumentCategory(String name, boolean required, boolean multiples) {
        this.name = name;
        this.required = required;
        this.multiples = multiples;
    }
}
