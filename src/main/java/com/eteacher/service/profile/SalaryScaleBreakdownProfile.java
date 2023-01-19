//package com.eteacher.service.profile;
//
//import com.eiin.entity.InstituteNearbyInstitutions;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//
//@Data
//public class SalaryScaleBreakdownProfile {
//
//    @NotNull
//    private Integer distanceKilometer;
//
//    @NotNull
//    private Integer distanceMeter;
//
//    @NotNull
//    private Long direction;
//
//    @NotNull
//    private Long institute;
//
//    @NotNull
//    private Long nearByInstitute;
//
//    public InstituteNearbyInstitutions to() {
//        InstituteNearbyInstitutions institutions = new InstituteNearbyInstitutions();
//        institutions.setDistanceKilometer(getDistanceKilometer());
//        institutions.setDistanceMeter(distanceMeter);
//        return institutions;
//    }
//}
