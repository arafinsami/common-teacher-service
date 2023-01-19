package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeEncloserDto {

    private Long id;

    private EncloserType encloserType;

    private String memorandumNo;

    private Date issueDate;

    private String encloserName;

    private String encloserNameBn;

    private String encloserUrl;

    public EmployeeEncloser to() {
        EmployeeEncloser encloser = new EmployeeEncloser();
        encloser.setEncloserType(encloserType);
        encloser.setMemorandumNo(memorandumNo);
        encloser.setIssueDate(issueDate);
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserUrl(encloserUrl);
        encloser.setRecordStatus(DRAFT);
        return encloser;
    }
}
