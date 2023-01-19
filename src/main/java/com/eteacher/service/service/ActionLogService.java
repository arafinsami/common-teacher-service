package com.eteacher.service.service;

import com.eteacher.service.entity.ActionLog;
import com.eteacher.service.enums.Action;
import com.eteacher.service.enums.ModuleName;
import com.eteacher.service.repository.ActionLogRepository;
import com.eteacher.service.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ActionLogService extends BaseService {

    private final ActionLogRepository repository;

    public ActionLog publishActivity(Action action, ModuleName name, Long documentId, String comments, String data) {

        ActionLog log = new ActionLog();
        log.setAction(action);
        log.setModuleName(name);
        log.setDocumentId(documentId);
        log.setIpAddress(WebUtils.getCurrentRequest().getRequestURI());
        log.setComments(comments);
        log.setBackupData(data.getBytes(StandardCharsets.UTF_8));
        repository.save(log);
        return null;
    }
}

