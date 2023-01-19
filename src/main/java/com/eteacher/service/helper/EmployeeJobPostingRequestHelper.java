package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeJobPostingRequestEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeJobPostingRequest;
import com.eteacher.service.entity.mpo.EmployeeJobPostingRequestEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeJobPostingRequestEncloserProfile;
import com.eteacher.service.profile.EmployeeJobPostingRequestProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_JOB_POSTING_REQUEST;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeJobPostingRequestHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeJobPostingRequestProfile, EmployeeJobPostingRequest> savePostingProfile = e -> {
        EmployeeJobPostingRequest record = e.to();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeJobPostingRequestEncloserProfile, EmployeeJobPostingRequestEncloser> saveEncloserProfiles = r -> {
        EmployeeJobPostingRequestEncloser encloser = r.to();
        encloser.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return encloser;
    };

    public Function<EmployeeJobPostingRequestProfile, EmployeeJobPostingRequest> updatePostingProfile = e -> {
        EmployeeJobPostingRequest record = e.update();
        record.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };


    public List<EmployeeJobPostingRequest> save(List<EmployeeJobPostingRequestProfile> profiles) {
        List<EmployeeJobPostingRequest> records = profiles
                .stream()
                .map(savePostingProfile)
                .collect(Collectors.toList());
        return records;
    }

    public List<EmployeeJobPostingRequestEncloser> save(MultipartFile[] files, EmployeeJobPostingRequestEncloserDto dto) {
        List<EmployeeJobPostingRequestEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeJobPostingRequestEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("jobPostingUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeJobPostingRequest> update(List<EmployeeJobPostingRequestProfile> profiles) {
        List<EmployeeJobPostingRequest> records = profiles
                .stream()
                .map(updatePostingProfile)
                .collect(Collectors.toList());
        return records;
    }

    public EmployeeJobPostingRequest findByPostingId(Long postingId, List<EmployeeJobPostingRequest> lists) {
        for (EmployeeJobPostingRequest jobPostingRequest : lists) {
            if (jobPostingRequest.getId().equals(postingId)) {
                return jobPostingRequest;
            }
        }
        return null;
    }

    public EmployeeJobPostingRequest findJobPosting(Long postingId, Employee employee) {
        return Optional.ofNullable(findByPostingId(postingId, employee.getJobPostingRequests()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_JOB_POSTING_REQUEST + postingId)
                );
    }

    public EmployeeJobPostingRequest deleteOne(EmployeeJobPostingRequest request) {
        request.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        request.setRecordStatus(DELETED);
        return request;
    }
}
