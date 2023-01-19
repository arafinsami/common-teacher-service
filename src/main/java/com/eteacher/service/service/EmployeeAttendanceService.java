package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeAttendanceAudit;
import com.eteacher.service.dto.EmployeeAttendanceDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeAttendanceHelper;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.EmployeeAttendanceRepository;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.EMPLOYEE_ATTENDANCE_DELETE;
import static com.eteacher.service.enums.Action.DELETE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeAttendanceService extends GenericQuery {

    private final EmployeeAttendanceRepository attendanceRepository;

    private final ActionLogService actionLogService;

    private final EmployeeRepository repository;

    private final EmployeeAttendanceHelper helper;

    private final EntityManager em;

    private final EmployeeService employeeService;

    public Map<String, Object> getList(String dateOfAttendance, String toDate, String fromDate, Long employee, Long department, Integer page, Integer size, String sortBy) throws ParseException {
        Date today = null;
        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        if (!dateOfAttendance.isEmpty()) today = isoFormat.parse(dateOfAttendance);
        if (!toDate.isEmpty()) startDate = isoFormat.parse(toDate);
        if (!fromDate.isEmpty()) endDate = isoFormat.parse(fromDate);
        GetListHelper<EmployeeAttendance> helper = new GetListHelper<>(em, EmployeeAttendance.class);
        return helper.getList(attendanceRepository.searchEmployeeAttendance(employee, department, today, startDate, endDate,
                helper.getPageable(sortBy, page, size)), page, size);
    }

    public Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Integer page, Integer size, Map<String, Object> filterMap) {
        return new GetListHelper<EmployeeAttendance>(em, EmployeeAttendance.class).getList(sortable, searchable, sortBy, search, filterMap, page, size);
    }

    public Optional<EmployeeAttendance> findById(Long id) {
        Optional<EmployeeAttendance> attendance = attendanceRepository.findById(id);
        return attendance;
    }

    @Transactional
    public void save(List<EmployeeAttendanceDto> dtos) {
        List<EmployeeAttendance> attendanceList = new ArrayList<>();
        Map<Long, List<EmployeeAttendance>> map = new HashMap<>();

        for (EmployeeAttendanceDto dto : dtos) {
            List<EmployeeAttendance> attendances = map.getOrDefault(dto.getEmployeeId(), attendanceList);
            attendances.add(dto.to());
            map.put(dto.getEmployeeId(), attendances);
        }

        for (var entry : map.entrySet()) {
            Employee employee = employeeService.findById(entry.getKey())
                    .orElseThrow(() -> new ResourceNotFoundException("Not Found Employee By " + entry.getKey()));
            employee.addAttendances(helper.save(entry.getValue()));
            repository.save(employee);
        }
    }

    @Transactional
    public void update(List<EmployeeAttendanceDto> dtos) {

        for (var dto : dtos) {
            Employee employee = employeeService.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found by " + dto.getEmployeeId()));
            EmployeeAttendance attendance = attendanceRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Attendance not found by " + dto.getId()));
            attendance = dto.update(attendance);
            attendance.setEmployee(employee);
            attendanceRepository.save(helper.update(attendance));
        }
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeAttendanceAudit> audits = e.getAttendances().stream()
                .map(EmployeeAttendanceAudit::from)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ATTENDANCE_DELETE,
                objectToJson(audits)
        );
    }
}
