package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeDto;
import com.eteacher.service.entity.commonteacher.*;
import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeHelper {

    private final ActiveUserContext context;

    private final DepartmentService deptService;

    private final EmployeeTypeService empTypeService;

    private final PaymentMethodService paymentMethodService;

    private final EmployeeEncloserService employeeEncloserService;

    @Resource
    private SalaryScaleService salaryScaleService;

    public void getSaveData(Employee employee) {
        employee.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        employee.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(Employee employee, RecordStatus status) {
        employee.setRecordVersion(employee.getRecordVersion() + 1);
        employee.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        employee.setRecordStatus(status);
    }

    public Employee getData(Employee employee, EmployeeDto dto) {
        Department department = deptService.findById(dto.getDepartment()).orElseThrow(ResourceNotFoundException::new);
        EmployeeType employeeType = empTypeService.findById(dto.getEmployeeType()).orElseThrow(ResourceNotFoundException::new);
        PaymentMethod paymentMethod = paymentMethodService.findById(dto.getPaymentMethod()).orElseThrow(ResourceNotFoundException::new);
        SalaryScale salaryScale = salaryScaleService.findById(dto.getSalaryScale()).orElseThrow(ResourceNotFoundException::new);
//        EmployeeEncloser encloser = employeeEncloserService.findById(dto.getEmployeeEncloser()).orElseThrow(ResourceNotFoundException::new);
        employee.setDepartment(department);
        employee.setEmployeeType(employeeType);
        employee.setPaymentMethod(paymentMethod);
        employee.setSalaryScale(salaryScale);
//        employee.setEmployeeEncloser(encloser);
        return employee;
    }
}
