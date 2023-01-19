package com.eteacher.service.resource;

import com.eteacher.service.dto.MailRequest;
import com.eteacher.service.response.EmployeeResponse;
import com.eteacher.service.system.EmailSenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail")
@Api(tags = "Mail Sending Demo")
public class MailResource {

    private final EmailSenderService service;

    @PostMapping("/send")
    @ApiOperation(value = "send mail", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> send(@Valid @RequestBody MailRequest request) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getEmail());
        mail.setFrom("sami.cse.1112@gmail.com");
        mail.setSubject("sent new token !!!");
        mail.setText("your new token Is : " + request.getEmail()
                + " please note that token will expire with in 6 hours" +
                " and everytime you will get new token");
        service.sendEmail(mail);
        return ok(success("mail sent").getJson());
    }
}
