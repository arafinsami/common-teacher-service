package com.eteacher.service.enums;

public enum ApplicantStatus {

    APPLICATION_SUBMITTED(1),
    APPLICATION_APPROVED(2),
    APPLICATION_REJECTED(3);

    private final Integer label;

    ApplicantStatus(Integer label) {
        this.label = label;
    }

    public Integer getLabel() {
        return label;
    }
}
