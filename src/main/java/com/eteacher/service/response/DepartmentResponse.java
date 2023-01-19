package com.eteacher.service.response;

import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import com.eteacher.service.profile.DepartmentalExaminationProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DepartmentResponse {

    private Long id;

    private String departmentName;

    private String departmentNameBn;

    private String description;

    private String descriptionBn;

    //private List<DepartmentalExaminationProfile> profiles;

    public static DepartmentResponse from(Department department) {
        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setDepartmentName(department.getDepartmentName());
        response.setDepartmentNameBn(department.getDepartmentNameBn());
        response.setDescription(department.getDescription());
        response.setDescriptionBn(department.getDescriptionBn());
        //response.setProfiles(getDeptExam(department.getExaminations()));
        return response;
    }

    /*public static List<DepartmentalExaminationProfile> getDeptExam(List<DepartmentalExamination> deptExams) {
        List<DepartmentalExaminationProfile> exams = deptExams.stream()
                .map(profile)
                .collect(Collectors.toList());
        return exams;
    }

    public static Function<DepartmentalExamination, DepartmentalExaminationProfile> profile = p -> {
        DepartmentalExaminationProfile deptExam = new DepartmentalExaminationProfile();
        deptExam.setId(p.getId());
        deptExam.setYear(p.getYear());
        return deptExam;
    };*/

}
