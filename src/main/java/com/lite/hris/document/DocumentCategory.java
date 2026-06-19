package com.lite.hris.document;

import lombok.Getter;

@Getter
public enum DocumentCategory {
    CITIZENSHIP("KTP", true, false, "personal"),
    TAX("NPWP",false, false,"personal"),
    FAMILY_REGISTRATION("KK", true, false,"personal"),
    CONTRACT_SIGNED("Contract Signed", true, true,"employment"),
    FORMAL_EDUCATION("Formal Education", true, true, "educational"),
    INFORMAL_EDUCATION("Informal Education", false, true,"educational"),
    CERTIFICATION("Certification", false, true, "certification");

    private final String name;
    private final boolean required;
    private final boolean multiples;
    private final String group;

    DocumentCategory(String name, boolean required, boolean multiples, String group) {
        this.name = name;
        this.required = required;
        this.multiples = multiples;
        this.group = group;
    }
}
