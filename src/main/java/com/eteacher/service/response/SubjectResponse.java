package com.eteacher.service.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectResponse {

    private Long id;
    private String subjectName;
    private String subjectNameBn;
    private String subjectDescription;
    private String subjectDescriptionBn;
    private String subjectCode;
    private Long subjectTypeId;
    private Integer subjectCredit;
    private Long instituteId;
    private Long parentSubjectId;
    private Boolean isGovt;
    private Boolean isNonGovt;
    private Boolean isMasters;
    private Boolean isHonours;
    private Long instituteTypeId;
}
