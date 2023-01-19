package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeBankAccountDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeBankAccount;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeBankAccountHelper;
import com.eteacher.service.response.EmployeeBankAccountResponse;
import com.eteacher.service.response.EmployeeResponse;
import com.eteacher.service.service.EmployeeBankAccountService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.validation.EmployeeBankAccountValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Bank Account Data")
@RequestMapping(
        "api/v1/employee-bank-account"
)
public class EmployeeBankAccountResource {

    private final EmployeeService employeeService;

    private final EmployeeBankAccountService service;

    private final EmployeeBankAccountHelper helper;

    private final EmployeeBankAccountValidator validator;

    @PostMapping("/save")
    @ApiOperation(value = "save employee bank account", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeBankAccountDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addBankAccounts(distinct(helper.save(dto.getBankAccounts())));

        service.save(employee);
        return ok(success(EMPLOYEE_BANK_ACCOUNT_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee bank account", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeBankAccountDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addBankAccounts(distinct(helper.update(dto.getBankAccounts())));

        service.update(employee);
        return ok(success(EMPLOYEE_BANK_ACCOUNT_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee all bank account by employee Id",
            response = EmployeeBankAccountResponse.class
    )
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        List<EmployeeBankAccount> accounts = employee.getBankAccounts()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeBankAccountResponse> response = accounts
                .stream()
                .map(EmployeeBankAccountResponse::from)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{accountId}")
    @ApiOperation(
            value = "get single employee bank account by employee Id and account Id",
            response = EmployeeBankAccountResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndAccountId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long accountId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeBankAccount account = helper.findByAccountId(accountId, employee.getBankAccounts());

        return ok(success(EmployeeBankAccountResponse.from(account)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{accountId}")
    @ApiOperation(value = "delete employee bank account by employee and account id",
            response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long accountId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeBankAccount account = helper.findByAccountId(accountId, employee.getBankAccounts());

        employee.addBankAccounts(helper.deleteOne(account));

        service.delete(employee);
        return ok(success(EMPLOYEE_BANK_ACCOUNT_DELETE + " " + accountId).getJson());
    }
}
