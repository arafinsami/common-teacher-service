package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import com.eteacher.service.entity.ntrca.EmployeePublication;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeePublicationProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.DEPARTMENTAL_EXAM_MERIT_SCORE;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_PUBLICATION;
import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class EmployeePublicationHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeePublicationProfile, EmployeePublication> savePublication = r -> {
        EmployeePublication application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(DRAFT);
        return application;
    };

    public Function<EmployeePublicationProfile, EmployeePublication> updatePublication = r -> {
        EmployeePublication application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(ACTIVE);
        return application;
    };

    public List<EmployeePublication> save(List<EmployeePublicationProfile> profiles) {
        List<EmployeePublication> applications = profiles.stream()
                .map(savePublication)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeePublication> update(List<EmployeePublicationProfile> profiles) {
        List<EmployeePublication> applications = profiles.stream()
                .map(updatePublication)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeePublication> delete(EmployeePublication application) {
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(DELETED);
        return Arrays.asList(application);
    }

    private EmployeePublication findById(Long id, List<EmployeePublication> lists) {
        for (EmployeePublication publication : lists) {
            if (publication.getId().equals(id)) {
                return publication;
            }
        }
        return null;
    }

    public EmployeePublication findPublication(Long id, Employee employee) {
        return Optional.ofNullable(findById(id, employee.getPublications()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_PUBLICATION + id)
                );
    }
}
