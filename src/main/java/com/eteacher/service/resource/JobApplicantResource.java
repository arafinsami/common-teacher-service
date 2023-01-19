package com.eteacher.service.resource;

import com.eteacher.service.dto.JobApplicantContactDto;
import com.eteacher.service.dto.JobApplicantDto;
import com.eteacher.service.dto.JobApplicantEducationalQualificationDto;
import com.eteacher.service.dto.JobApplicantJobExperienceDto;
import com.eteacher.service.entity.job.*;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.JobApplicantHelper;
import com.eteacher.service.response.*;
import com.eteacher.service.service.JobApplicantService;
import com.eteacher.service.service.JobCircularService;
import com.eteacher.service.validation.JobApplicantValidator;
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
import static com.eteacher.service.constant.NotFoundConstant.JOB_APPLICANT;
import static com.eteacher.service.constant.NotFoundConstant.JOB_CIRCULAR;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "JOB--->Job applicant's Data")
@RequestMapping(
        "api/v1/job-applicant"
)
public class JobApplicantResource {

    private final JobApplicantService service;

    private final JobCircularService circularService;

    private final JobApplicantHelper helper;

    private final JobApplicantValidator validator;

    private final String[] sortable = {"id", "applicantName"};

    @GetMapping("/all")
    @ApiOperation(value = "Get all employee type", response = JobApplicantResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {

        List<JobApplicantResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(JobApplicantResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @PostMapping("/save")
    @ApiOperation(value = "save job applicant", response = JobApplicantResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody JobApplicantDto dto, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = dto.to();

        JobCircular circular = circularService.findById(dto.getJobCircular()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_CIRCULAR + dto.getJobCircular())
        );

        applicant.setJobCircular(circular);

        service.save(applicant);
        return ok(success(JobApplicantResponse.response(applicant), JOB_APPLICANT_SAVE).getJson());
    }

    @PostMapping("/experiences/save")
    @ApiOperation(
            value = "save applicant experience info with applicant Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveExperiences(@Valid @RequestBody JobApplicantJobExperienceDto dto,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getApplicantId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getApplicantId())
        );

        applicant.addExperiences(distinct(helper.saveExperiences(dto.getExperiences())));

        service.saveExperiences(applicant);
        return ok(success(JOB_APPLICANT_EXPERIENCE_SAVE).getJson());
    }

    @PostMapping("/qualifications/save")
    @ApiOperation(
            value = "save job applicant educational qualifications info",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveQualifications(@Valid @RequestBody JobApplicantEducationalQualificationDto dto,
                                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getApplicantId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getApplicantId())
        );

        applicant.addQualifications(distinct(helper.saveQualifications(dto.getQualifications())));

        service.saveQualifications(applicant);
        return ok(success(JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_SAVE).getJson());
    }

    @PostMapping("/contacts/save")
    @ApiOperation(
            value = "save job applicant contact info",
            response = JobApplicantResponse.class
    )
    public ResponseEntity<JSONObject> saveContacts(@Valid @RequestBody JobApplicantContactDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getApplicantId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getApplicantId())
        );

        applicant.addContacts(distinct(helper.saveContacts(dto.getContacts())));

        service.saveContacts(applicant);
        return ok(success(JOB_APPLICANT_CONTACT_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update job applicant", response = JobApplicantResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody JobApplicantDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getId())
        );

        JobCircular circular = circularService.findById(dto.getJobCircular()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_CIRCULAR + dto.getJobCircular())
        );

        dto.update(applicant);

        applicant.setJobCircular(circular);

        service.update(applicant);
        return ok(success(JobApplicantResponse.response(applicant), JOB_APPLICANT_UPDATE).getJson());
    }

    @PutMapping("/experiences/update")
    @ApiOperation(
            value = "update job applicant experience info",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateExperiences(@Valid @RequestBody JobApplicantJobExperienceDto dto,
                                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getApplicantId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getApplicantId())
        );

        applicant.addExperiences(distinct(helper.updateExperiences(dto.getExperiences())));

        service.updateExperiences(applicant);
        return ok(success(JOB_APPLICANT_EXPERIENCE_UPDATE).getJson());
    }

    @PutMapping("/qualifications/update")
    @ApiOperation(
            value = "update job applicant educational qualifications info",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateQualifications(@Valid @RequestBody JobApplicantEducationalQualificationDto dto,
                                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getApplicantId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getApplicantId())
        );

        applicant.addQualifications(distinct(helper.updateQualifications(dto.getQualifications())));

        service.updateQualifications(applicant);
        return ok(success(JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_UPDATE).getJson());
    }

    @PutMapping("/contacts/update")
    @ApiOperation(value = "update job applicant contact info", response = String.class)
    public ResponseEntity<JSONObject> updateContacts(@Valid @RequestBody JobApplicantContactDto dto,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobApplicant applicant = service.findById(dto.getApplicantId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + dto.getApplicantId())
        );

        applicant.addContacts(distinct(helper.updateContacts(dto.getContacts())));

        service.updateContacts(applicant);
        return ok(success(JOB_APPLICANT_CONTACT_UPDATE).getJson());
    }

    @GetMapping("/find/applicant/{applicantId}")
    @ApiOperation(value = "get job applicant applicant Id", response = JobApplicantResponse.class)
    public ResponseEntity<JSONObject> findByJobApplicantId(@Valid @PathVariable @NotNull @Min(1) Long applicantId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        return ok(success(JobApplicantResponse.response(applicant)).getJson());
    }

    @GetMapping("/find/experiences/{applicantId}")
    @ApiOperation(
            value = "get experience lists by applicant Id",
            response = JobApplicantJobExperienceResponse.class
    )
    public ResponseEntity<JSONObject> findAllExperiencesByJobApplicantId(@Valid @PathVariable @NotNull @Min(1) Long applicantId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        List<JobApplicantJobExperience> experiences = applicant.getExperiences()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .collect(Collectors.toList());

        List<JobApplicantJobExperienceResponse> responses = experiences
                .stream()
                .map(JobApplicantJobExperienceResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/qualification/{applicantId}")
    @ApiOperation(
            value = "get educational qualification lists by applicant Id",
            response = JobApplicantEducationalQualificationResponse.class
    )
    public ResponseEntity<JSONObject> findAllQualificationByJobApplicantId(@Valid @PathVariable @NotNull @Min(1) Long applicantId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        List<JobApplicantEducationalQualification> qualifications = applicant.getQualifications()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .collect(Collectors.toList());

        List<JobApplicantEducationalQualificationResponse> responses = qualifications
                .stream()
                .map(JobApplicantEducationalQualificationResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/contacts/{applicantId}")
    @ApiOperation(
            value = "get contact lists by applicant Id",
            response = JobApplicantContactResponse.class
    )
    public ResponseEntity<JSONObject> findAllContactsByJobApplicantId(@Valid @PathVariable @NotNull @Min(1) Long applicantId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        List<JobApplicantContact> contacts = applicant.getContacts()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .collect(Collectors.toList());

        List<JobApplicantContactResponse> responses = contacts
                .stream()
                .map(JobApplicantContactResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/experience/{applicantId}/{experienceId}")
    @ApiOperation(
            value = "get job experience by applicant Id and experience Id",
            response = JobApplicantJobExperienceResponse.class
    )
    public ResponseEntity<JSONObject> findExperienceIdByJobApplicantIdAndExperienceId(@Valid @PathVariable @NotNull @Min(1) Long applicantId,
                                                                                      @Valid @PathVariable @NotNull @Min(1) Long experienceId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        JobApplicantJobExperience experience = helper.findExperience(experienceId, applicant);

        return ok(success(JobApplicantJobExperienceResponse.response(experience)).getJson());
    }

    @GetMapping("/find/qualification/{applicantId}/{qualificationId}")
    @ApiOperation(
            value = "get educational qualification by applicant Id and qualification Id",
            response = JobApplicantEducationalQualificationResponse.class
    )
    public ResponseEntity<JSONObject> findQualificationByJobApplicantIdAndQualificationId(@Valid @PathVariable @NotNull @Min(1) Long applicantId,
                                                                                          @Valid @PathVariable @NotNull @Min(1) Long qualificationId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        JobApplicantEducationalQualification qualification = helper.findQualification(qualificationId, applicant);

        return ok(success(JobApplicantEducationalQualificationResponse.response(qualification)).getJson());
    }

    @GetMapping("/find/contact/{applicantId}/{contactId}")
    @ApiOperation(
            value = "get contact by applicant Id and contact Id",
            response = JobApplicantContactResponse.class
    )
    public ResponseEntity<JSONObject> findContactByJobApplicantIdAndContactId(@Valid @PathVariable @NotNull @Min(1) Long applicantId,
                                                                              @Valid @PathVariable @NotNull @Min(1) Long contactId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        JobApplicantContact contact = helper.findContact(contactId, applicant);

        return ok(success(JobApplicantContactResponse.response(contact)).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete job applicant by id", response = String.class)
    public ResponseEntity<JSONObject> update(@PathVariable Long id) {

        JobApplicant applicant = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + id)
        );

        service.delete(applicant);
        return ok(success(JOB_APPLICANT_DELETE).getJson());
    }

    @DeleteMapping("/delete/experience/{applicantId}/{experienceId}")
    @ApiOperation(
            value = "delete experience by applicant Id and experience Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteExperience(@Valid @PathVariable @NotNull @Min(1) Long applicantId,
                                                       @Valid @PathVariable @NotNull @Min(1) Long experienceId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        JobApplicantJobExperience experience = helper.findExperience(experienceId, applicant);

        applicant.addExperiences(helper.deleteExperience(experience));

        service.deleteExperience(applicant);
        return ok(success(JOB_APPLICANT_EXPERIENCE_DELETE + " with id: " + experienceId).getJson());
    }

    @DeleteMapping("/delete/qualification/{applicantId}/{qualificationId}")
    @ApiOperation(
            value = "delete qualification by applicant Id and qualification Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteQualification(@Valid @PathVariable @NotNull @Min(1) Long applicantId,
                                                          @Valid @PathVariable @NotNull @Min(1) Long qualificationId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        JobApplicantEducationalQualification qualification = helper.findQualification(qualificationId, applicant);

        applicant.addQualifications(helper.deleteQualification(qualification));

        service.deleteQualification(applicant);
        return ok(success(JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_DELETE + " with id: " + qualificationId).getJson());
    }

    @DeleteMapping("/delete/contact/{applicantId}/{contactId}")
    @ApiOperation(
            value = "delete contact by applicant Id and contact Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteContact(@Valid @PathVariable @NotNull @Min(1) Long applicantId,
                                                    @Valid @PathVariable @NotNull @Min(1) Long contactId) {

        JobApplicant applicant = service.findById(applicantId).orElseThrow(
                () -> new ResourceNotFoundException(JOB_APPLICANT + applicantId)
        );

        JobApplicantContact contact = helper.findContact(contactId, applicant);

        applicant.addContacts(helper.deleteContact(contact));

        service.deleteContact(applicant);
        return ok(success(JOB_APPLICANT_CONTACT_DELETE + " with id: " + contactId).getJson());
    }
}
