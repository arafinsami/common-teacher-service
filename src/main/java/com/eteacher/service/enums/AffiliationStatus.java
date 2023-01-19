package com.eteacher.service.enums;

public enum AffiliationStatus {

    APPLIED("Applied"),
    APPROVED("Approved"),
    CANCELED("Canceled"),
    NONE("None");

    private final String label;

    AffiliationStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
