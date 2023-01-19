package com.eteacher.service.enums;

public enum JobNature {

    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    CONTRACTUAL("Contractual");

    private final String label;

    JobNature(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
