package br.com.microservices.microservices.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.microservices.Model.DTO.EmailDTO;
import br.com.microservices.microservices.Services.SendEmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    final SendEmailService sendEmailService;

    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("/enviaremail")
    public void sendEmail(@RequestBody EmailDTO emailDTO) {
        sendEmailService.sendEmail(emailDTO);

    }

}
