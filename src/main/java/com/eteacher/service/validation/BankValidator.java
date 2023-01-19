package com.eteacher.service.validation;

import com.eteacher.service.dto.BankDto;
import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isEmpty;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class BankValidator implements Validator {

    @Resource
    private BankService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return BankDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        BankDto dto = (BankDto) target;

        if (isEmpty(dto.getId())) {
            Optional<Bank> bankName = service.findByBankName(dto.getBankName());
            if (bankName.isPresent()) {
                error.rejectValue("bankName", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(dto.getId())) {
            Bank bank = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!bank.getBankName().equals(dto.getBankName())) {
                Optional<Bank> bankName = service.findByBankName(dto.getBankName());
                if (bankName.isPresent()) {
                    error.rejectValue("bankName", null, ALREADY_EXIST);
                }
            }
        }

    }
}
