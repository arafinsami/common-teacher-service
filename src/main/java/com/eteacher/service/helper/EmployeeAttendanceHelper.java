package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeAttendanceDto;
import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.profile.EmployeeAttendanceProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeAttendanceHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeAttendance, EmployeeAttendance> saveProfile = converter -> {
        converter.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return converter;
    };

    public Function<EmployeeAttendance, EmployeeAttendance> updateProfile = converter -> {
        converter.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return converter;
    };

    public EmployeeAttendance saveBaseData(EmployeeAttendanceDto dto){
        EmployeeAttendance attendance = dto.to();
        attendance.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return attendance;
    }

    public EmployeeAttendance updateBaseData(EmployeeAttendanceDto dto){
        EmployeeAttendance attendance = dto.update();
        attendance.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return attendance;
    }

    public List<EmployeeAttendance> save(List<EmployeeAttendance> attendances) {
        attendances.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return attendances;
    }

    public EmployeeAttendance update(EmployeeAttendance attendance) {
        /*attendances.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return attendances;*/
        attendance.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return attendance;
    }

    public List<EmployeeAttendance> deleteOne(EmployeeAttendance attendance) {
        attendance.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        attendance.setRecordStatus(DELETED);
        return Arrays.asList(attendance);
    }

    public Optional<EmployeeAttendance> findByAttendanceId(Long attendanceId, List<EmployeeAttendance> attendances) {
        for (EmployeeAttendance attendance : attendances) {
            if (attendance.getId().equals(attendanceId)) {
                return Optional.of(attendance);
            }
        }

        return Optional.empty();
    }
}
