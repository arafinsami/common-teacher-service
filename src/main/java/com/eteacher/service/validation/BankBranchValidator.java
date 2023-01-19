package com.eteacher.service.validation;

import com.eteacher.service.dto.BankBranchDto;
import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.BankService;
import com.eteacher.service.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.*;

@Component
@RequiredArgsConstructor
public class BankBranchValidator implements Validator {

    @Resource
    private BankService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return BankBranchDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        BankBranchDto dto = (BankBranchDto) target;

        if (CollectionUtils.isNotEmpty(dto.getProfiles())) {
            IntStream.range(0, dto.getProfiles().size()).forEach(f -> {
                if (isNull(dto.getProfiles().get(f).getId())) {
                    if (isNotEmpty(dto.getProfiles().get(f).getBankBranchName())) {
                        Bank bank = service.findById(dto.getBankId())
                                .orElseThrow(ResourceNotFoundException::new);
                        Optional<BankBranch> bankBranch = service
                                .findByBankBranchName(dto.getProfiles().get(f).getBankBranchName(), bank);
                        if (bankBranch.isPresent()) {
                            error.rejectValue("profiles[" + f + "].bankBranchName", null, ALREADY_EXIST);
                        }
                    }
                }

                if (nonNull(dto.getProfiles().get(f).getId())) {
                    if (isNotEmpty(dto.getProfiles().get(f).getBankBranchName())) {
                        BankBranch bankBranch = service.findByBankBranchId(dto.getProfiles().get(f).getId())
                                .orElseThrow(ResourceNotFoundException::new);
                        if (!bankBranch.getBankBranchName().equals(dto.getProfiles().get(f).getBankBranchName())) {
                            Bank bank = service.findById(dto.getBankId())
                                    .orElseThrow(ResourceNotFoundException::new);
                            Optional<BankBranch> branchName = service.findByBankBranchName(dto.getProfiles().get(f).getBankBranchName(), bank);
                            if (branchName.isPresent()) {
                                error.rejectValue("profiles[" + f + "].bankBranchName", null, ALREADY_EXIST);
                            }
                        }
                    }
                }
            });
        }
    }
}
