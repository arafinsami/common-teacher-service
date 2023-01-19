package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import com.eteacher.service.entity.composite.SalaryScaleBreakdownPK;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.repository.SalaryBreakdownRepository;
import com.eteacher.service.repository.SalaryScaleRepository;
import com.eteacher.service.dto.SalaryScaleBreakdownDto;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SalaryScaleBreakdownHelper {

    private final ActiveUserContext context;

    private final SalaryScaleRepository salaryScaleRepository;

    private final SalaryBreakdownRepository salaryBreakdownRepository;

    public void getSaveData(SalaryScaleBreakdownDto request, SalaryScaleBreakdown salaryScaleBreakdown) {
        salaryScaleBreakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        salaryScaleBreakdown.setRecordStatus(RecordStatus.DRAFT);
        SalaryScale salaryScale = getSalaryScale(request.getSalaryScaleId());
        SalaryBreakdown salaryBreakdown = getSalaryBreakdown(request.getSalaryBreakDownId());
        SalaryScaleBreakdownPK salaryScaleBreakdownPK = new SalaryScaleBreakdownPK();
        salaryScaleBreakdownPK.setSalaryScaleId(salaryScale.getId());
        salaryScaleBreakdownPK.setSalaryBreakdownId(salaryBreakdown.getId());
        salaryScaleBreakdown.setSalaryScaleBreakdownPK(salaryScaleBreakdownPK);
    }

    public void getUpdateData(SalaryScaleBreakdown salaryScaleBreakdown, RecordStatus status) {
        salaryScaleBreakdown.setRecordVersion(salaryScaleBreakdown.getRecordVersion() + 1);
        salaryScaleBreakdown.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        salaryScaleBreakdown.setRecordStatus(status);
    }

    public SalaryScale getSalaryScale(Long id) {
        SalaryScale salaryScale = salaryScaleRepository.findByIdAndRecordStatusNot(id, RecordStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);
        return salaryScale;
    }

    public SalaryBreakdown getSalaryBreakdown(Long id) {
        SalaryBreakdown salaryBreakdown = salaryBreakdownRepository.findByIdAndRecordStatusNot(id, RecordStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);
        return salaryBreakdown;
    }
}
