package com.eteacher.service.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DesignationResponse {

    private Long id;

    private Long employeeTypeId;

    private String designationName;

    private String designationNameBn;

    private String designationCode;

    private String designationDescription;

    private String designationDescriptionBn;
}
