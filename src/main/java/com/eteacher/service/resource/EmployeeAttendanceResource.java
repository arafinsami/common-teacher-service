package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeAttendanceDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeAttendanceHelper;
import com.eteacher.service.response.EmployeeAttendanceResponse;
import com.eteacher.service.response.EmployeeAttendanceSummeryResponse;
import com.eteacher.service.response.EmployeeResponse;
import com.eteacher.service.service.EmployeeAttendanceService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.utils.CommonDataHelper;
import com.eteacher.service.utils.PaginatedResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.utils.ResponseBuilder.paginatedSuccess;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Attendance Data")
@RequestMapping("api/v1/employee-attendance")
public class EmployeeAttendanceResource {

    private final EmployeeService employeeService;

    private final EmployeeAttendanceService service;

    private final EmployeeAttendanceHelper helper;

    private final CommonDataHelper commonHelper;

    @PostMapping("/save")
    @ApiOperation(value = "save employee attendance", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody List<EmployeeAttendanceDto> dtos) {

        service.save(dtos);
        return ok(success(null, EMPLOYEE_ATTENDANCE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee attendance", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody List<EmployeeAttendanceDto> dto) {

        service.update(dto);
        return ok(success(null, EMPLOYEE_ATTENDANCE_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{attendanceId}")
    @ApiOperation(value = "get single employee attendance by employee Id and attendance Id", response = EmployeeAttendanceResponse.class)
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndAttendanceId(@Valid @PathVariable @NotNull @Min(1) Long employeeId, @Valid @PathVariable @NotNull @Min(1) Long attendanceId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeAttendance attendance = helper.findByAttendanceId(attendanceId, employee.getAttendances()).orElseThrow(ResourceNotFoundException::new);


        return ok(success(EmployeeAttendanceResponse.response(attendance)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{attendanceId}")
    @ApiOperation(value = "delete employee attendance by employee and attendance id", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> deleteAttendance(@Valid @PathVariable @NotNull @Min(1) Long employeeId, @Valid @PathVariable @NotNull @Min(1) Long attendanceId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeAttendance attendance = helper.findByAttendanceId(attendanceId, employee.getAttendances()).orElseThrow(ResourceNotFoundException::new);

        employee.addAttendances(helper.deleteOne(attendance));

        service.delete(employee);
        return ok(success(null, EMPLOYEE_ATTENDANCE_DELETE + " " + attendanceId).getJson());
    }

    @GetMapping("/list")
    @ApiOperation(value = "get transfer certificate list", response = EmployeeAttendanceResponse.class)
    public ResponseEntity<JSONObject> getList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, @RequestParam(value = "sortBy", defaultValue = "") String sortBy, @RequestParam(value = "dateOfAttendance", defaultValue = "") String dateOfAttendance, @RequestParam(value = "toDate", defaultValue = "") String toDate, @RequestParam(value = "fromDate", defaultValue = "") String fromDate, @RequestParam(value = "employeeId", defaultValue = "") Long employee, @RequestParam(value = "departmentId", defaultValue = "") Long department) throws ParseException {

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> attendanceMap = service.getList(dateOfAttendance, fromDate, toDate, employee, department, page, size, sortBy);

        List<EmployeeAttendance> attendances = (List<EmployeeAttendance>) attendanceMap.get("lists");

        List<EmployeeAttendanceResponse> responses = attendances.stream()
                .map(EmployeeAttendanceResponse::response).collect(Collectors.toList());

        commonHelper.getCommonData(page, size, attendanceMap, response, responses);

        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/employee-attendance-list-summery")
    @ApiOperation(value = "get transfer certificate list", response = EmployeeAttendanceResponse.class)
    public ResponseEntity<JSONObject> employeeAttendanceList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, @RequestParam(value = "sortBy", defaultValue = "") String sortBy, @RequestParam(value = "dateOfAttendance", defaultValue = "") String dateOfAttendance, @RequestParam(value = "toDate", defaultValue = "") String toDate, @RequestParam(value = "fromDate", defaultValue = "") String fromDate, @RequestParam(value = "employeeId", defaultValue = "") Long employee, @RequestParam(value = "departmentId", defaultValue = "") Long department) throws ParseException {

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> attendanceMap = service.getList(dateOfAttendance, fromDate, toDate, employee, department, page, size, sortBy);

        List<EmployeeAttendance> attendances = (List<EmployeeAttendance>) attendanceMap.get("lists");

        Map<Long, EmployeeAttendanceSummeryResponse> map = new HashMap<>();
        for(var a:attendances){
            EmployeeAttendanceSummeryResponse r = new EmployeeAttendanceSummeryResponse();
            if(map.containsKey(a.getEmployee().getId())){
                r.setEmployeeId(a.getEmployee().getId());
                r.setEmployeeName(a.getEmployee().getEmployeeName());
                switch(a.getStatus()){
                    case ABSENT:
                        r.setTotalAbsent(map.get(a.getEmployee().getId()).getTotalAbsent() + 1);
                        break;
                    case PRESENT:
                        r.setTotalPresent(map.get(a.getEmployee().getId()).getTotalPresent() + 1);
                        break;
                    case LATE:
                        r.setTotalDay(map.get(a.getEmployee().getId()).getTotalLate() + 1);
                }
                if(a.getIsLateAbsentApproved())
                    r.setTotalLateAbsentApproved(map.get(a.getEmployee().getId()).getTotalLateAbsentApproved() + 1);
                else{
                    r.setTotalLateAbsentApproved(map.get(a.getEmployee().getId()).getTotalLateAbsentApproved());
                }
                map.put(a.getEmployee().getId(), r);
            }else {
                r.setEmployeeId(a.getEmployee().getId());
                r.setEmployeeName(a.getEmployee().getEmployeeName());
                switch(a.getStatus()){
                    case ABSENT:
                        r.setTotalAbsent(1);
                        break;
                    case PRESENT:
                        r.setTotalPresent(1);
                        break;
                    case LATE:
                        r.setTotalDay(1);
                }
                if(a.getIsLateAbsentApproved())
                    r.setTotalLateAbsentApproved(1);
                map.put(a.getEmployee().getId(), r);
            }

        }

        List<EmployeeAttendanceSummeryResponse> responses = new ArrayList<>(map.values());

        commonHelper.getCommonData(page, size, attendanceMap, response, responses);

        return ok(paginatedSuccess(response).getJson());
    }
}
