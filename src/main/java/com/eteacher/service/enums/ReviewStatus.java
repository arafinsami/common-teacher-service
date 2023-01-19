package com.eteacher.service.enums;

public enum ReviewStatus {

    ACTIVE("Active"),
    IN_ACTIVE("In Active"),
    DOING("Doing"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String label;

    ReviewStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
