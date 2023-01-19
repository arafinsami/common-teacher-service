package com.eteacher.service.enums;

public enum ProficiencyLevel {

    NONE("No Proficiency"),
    ELEMENTARY("Elementary Proficiency"),
    LIMITED("Limited Working Proficiency"),
    PROFESSIONAL("Professional Working Proficiency"),
    FULL_PROFESSIONAL("Full Professional Proficiency"),
    NATIVE_BILINGUAL("Native/Bilingual Proficiency");

    private final String label;

    ProficiencyLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
