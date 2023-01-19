package com.eteacher.service.resource;

import com.eteacher.service.dto.BankBranchDto;
import com.eteacher.service.dto.BankDto;
import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.BankHelper;
import com.eteacher.service.response.BankBranchResponse;
import com.eteacher.service.response.BankResponse;
import com.eteacher.service.service.BankService;
import com.eteacher.service.validation.BankBranchValidator;
import com.eteacher.service.validation.BankValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Bank Data")
@RequestMapping(
        "/api/v1/bank"
)
public class BankResource {

    private final BankService service;

    private final BankHelper helper;

    private final BankBranchValidator bankBranchValidator;

    private final BankValidator bankValidator;

    @PostMapping("/save")
    @ApiOperation(value = "save bank", response = BankResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody BankDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(bankValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Bank bank = dto.to();

        service.save(bank);
        return ok(success(BankResponse.from(bank), BANK_SAVE).getJson());
    }

    @PostMapping("/branch/save")
    @ApiOperation(value = "save bank branch by bank Id", response = Object.class)
    public ResponseEntity<JSONObject> saveBranch(@RequestBody @Valid BankBranchDto dto,
                                                 BindingResult bindingResult) {

        ValidationUtils.invokeValidator(bankBranchValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Bank bank = service.findById(dto.getBankId()).orElseThrow(ResourceNotFoundException::new);

        bank.addBranchs(helper.saveBranches(dto.getProfiles()));

        service.saveBranch(bank);
        return ok(success(BANK_BRANCH_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "bank update", response = BankResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody BankDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(bankValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Bank bank = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(bank);

        service.update(bank);
        return ok(success(BankResponse.from(bank), BANK_UPDATE).getJson());
    }

    @PutMapping("/branch/update")
    @ApiOperation(value = "update bank branch by bank Id", response = Object.class)
    public ResponseEntity<JSONObject> updateBranch(@RequestBody @Valid BankBranchDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Bank bank = service.findById(dto.getBankId()).orElseThrow(ResourceNotFoundException::new);

        bank.addBranchs(helper.updateBranches(dto.getProfiles()));

        service.updateBranch(bank);
        return ok(success(BANK_BRANCH_UPDATE).getJson());
    }

    @GetMapping("/find/{bankId}")
    @ApiOperation(value = "get bank by bank Id", response = BankResponse.class)
    public ResponseEntity<JSONObject> findByBankId(@PathVariable Long bankId) {

        BankResponse response = service.findById(bankId)
                .map(BankResponse::from)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @GetMapping("/find/branch/{bankId}")
    @ApiOperation(
            value = "get all bank branch by bankId",
            response = BankBranchResponse.class
    )
    public ResponseEntity<JSONObject> findBranchByBankId(@PathVariable Long bankId) {

        Bank bank = service.findById(bankId).orElseThrow(ResourceNotFoundException::new);

        List<BankBranch> branches = bank.getBankBranches()
                .stream()
                .filter(f-> f.getRecordStatus() != DELETED)
                .collect(Collectors.toList());

        List<BankBranchResponse> responses = branches
                .stream()
                .map(BankBranchResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/branch/{bankId}/{branchId}")
    @ApiOperation(
            value = "get single bank branch by bank Id and branch Id",
            response = BankBranchResponse.class
    )
    public ResponseEntity<JSONObject> findBranchByBankIdAndBranchId(@PathVariable Long bankId, @PathVariable Long branchId) {

        Bank bank = service.findById(bankId).orElseThrow(ResourceNotFoundException::new);

        BankBranch branch = helper.findByBranchId(branchId, bank.getBankBranches());
        return ok(success(BankBranchResponse.response(branch)).getJson());
    }

    @DeleteMapping("/delete/{bankId}")
    @ApiOperation(value = "delete bank by bank Id", response = BankResponse.class)
    public ResponseEntity<JSONObject> deleteBank(@PathVariable Long bankId) {

        Bank bank = service.findById(bankId).orElseThrow(ResourceNotFoundException::new);

        service.delete(bank);
        return ok(success(BANK_DELETE + bankId).getJson());
    }

    @DeleteMapping("/delete/branch/{bankId}/{branchId}")
    @ApiOperation(value = "delete bank branch by bank Id and branch Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteBankBranch(@Valid @PathVariable @NotNull @Min(1) Long bankId,
                                                       @Valid @PathVariable @NotNull @Min(1) Long branchId) {

        Bank bank = service.findById(bankId).orElseThrow(ResourceNotFoundException::new);

        BankBranch branch = helper.findByBranchId(branchId, bank.getBankBranches());

        bank.addBranchs(helper.deleteBranch(branch));

        service.deleteBranch(bank);
        return ok(success(BANK_BRANCH_DELETE + " with id: " + branchId).getJson());
    }
}

