package com.eteacher.service.dto;

import com.eteacher.service.profile.BankBranchProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BankBranchDto {

    @NotNull
    private Long bankId;

    @Valid
    private List<BankBranchProfile> profiles;
}
