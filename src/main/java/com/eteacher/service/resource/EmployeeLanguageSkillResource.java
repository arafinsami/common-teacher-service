package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeLanguageSkillDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeLanguageSkillHelper;
import com.eteacher.service.response.EmployeeLanguageSkillResponse;
import com.eteacher.service.service.EmployeeLanguageSkillService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.validation.EmployeeLanguageSkillValidator;
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
@Api(tags = "COMMON TEACHER---->Employee Language Skill Data")
@RequestMapping(
        "api/v1/employee-language-skill"
)
public class EmployeeLanguageSkillResource {

    private final EmployeeService employeeService;

    private final EmployeeLanguageSkillService service;

    private final EmployeeLanguageSkillHelper helper;

    private final EmployeeLanguageSkillValidator languageSkillValidator;

    @PostMapping("/save")
    @ApiOperation(value = "save employee language skill", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeLanguageSkillDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(languageSkillValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addLanguageSkills(distinct(helper.save(dto.getLanguageSkills())));

        service.save(employee);
        return ok(success(EMPLOYEE_LANGUAGE_SKILL_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee language skill", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeLanguageSkillDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(languageSkillValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addLanguageSkills(distinct(helper.update(dto.getLanguageSkills())));

        service.update(employee);
        return ok(success(EMPLOYEE_LANGUAGE_SKILL_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee language skill by employee Id",
            response = EmployeeLanguageSkillResponse.class
    )
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        List<EmployeeLanguageSkill> skills = employee.getLanguageSkills()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeLanguageSkillResponse> response = skills
                .stream()
                .map(EmployeeLanguageSkillResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{skillId}")
    @ApiOperation(
            value = "get single employee language skill by employee Id and skill Id",
            response = EmployeeLanguageSkillResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndSkillId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                    @Valid @PathVariable @NotNull @Min(1) Long skillId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeLanguageSkill skill = helper.findBySkillId(skillId, employee.getLanguageSkills());

        return ok(success(EmployeeLanguageSkillResponse.response(skill)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{skillId}")
    @ApiOperation(value = "delete employee language skill by employee Id and skill Id", response = String.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long skillId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeLanguageSkill skill = helper.findBySkillId(skillId, employee.getLanguageSkills());

        employee.addLanguageSkills(helper.deleteOne(skill));

        service.delete(employee);
        return ok(success(EMPLOYEE_LANGUAGE_SKILL_DELETE + " " + skillId).getJson());
    }
}
