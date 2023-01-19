package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecordEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.DRAFT;


@Data
@NoArgsConstructor
public class EmployeeLeaveRecordEncloserProfile {

    private Long id;

    @NotNull
    private EncloserType encloserType;

    private String memorandumNo;

    @NotNull
    private Date issueDate;

    @NotBlank
    private String encloserName;

    @NotBlank
    private String encloserNameBn;

    private String encloserUrl;

    public EmployeeLeaveRecordEncloser to() {
        EmployeeLeaveRecordEncloser encloser = new EmployeeLeaveRecordEncloser();
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
