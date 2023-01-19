package com.eteacher.service.service;

import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import com.eteacher.service.helper.EmployeeTrainingDetailsHelper;
import com.eteacher.service.repository.EmployeeTrainingDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeTrainingService extends BaseService {

    private final EmployeeTrainingDetailsRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeTrainingDetailsHelper trainingDetailsHelper;

    public Optional<EmployeeTrainingDetail> findById(Long id) {
        Optional<EmployeeTrainingDetail> detail = repository.findById(id);
        return  detail;
    }
}
