package com.eteacher.service.enums;

public enum TransferStatus {

    NONE("None"),
    APPLIED("Applied"),
    APPROVED("Approved"),
    HOLD("Hold"),
    CANCELED("Canceled");

    private final String label;

    TransferStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
