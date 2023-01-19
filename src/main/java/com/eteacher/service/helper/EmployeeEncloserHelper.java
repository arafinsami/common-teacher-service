package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeEncloserDto;
import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.function.Function;

@Component
public class EmployeeEncloserHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeEncloserDto, EmployeeEncloser> saveDto = r -> {
        EmployeeEncloser encloser = r.to();
        encloser.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return encloser;
    };

    public EmployeeEncloser save(MultipartFile file, EmployeeEncloserDto dto) {
        EmployeeEncloser encloser = saveDto.apply(dto);
        if (encloser.getEncloserUrl().equals(file.getOriginalFilename())) {
            encloser.setEncloserUrl(
                    upload(file, env.getProperty("employeeEncloserUploadPath"))
            );
        }
        return encloser;
    }
}
