package com.eteacher.service.enums;

public enum TrainingType {

    NONE("None"),
    ACADEMIC("Academic"),
    EMPLOYMENT("Employment"),
    PROFESSIONAL("professional"),
    DEPARTMENT_FOUNDATIONAL("Departmental Foundational");

    private String label;

    TrainingType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
