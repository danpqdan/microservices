package br.com.microservices.microservices.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.microservices.microservices.Model.Users;
import br.com.microservices.microservices.Model.DTO.EmailDTO;
import br.com.microservices.microservices.Repository.UserRepository;

@Service
public class SendEmailService {

    UserRepository userRepository;
    JavaMailSender emailSender;

    public SendEmailService(JavaMailSender emailSender, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.username}")
    String remetende;

    @Value("${comment.send}")
    String titte;

    public void sendEmail(EmailDTO emailDTO) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetende);
            message.setTo(emailDTO.getTo());
            message.setSubject(titte);
            message.setText(emailDTO.getBody());
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailNewUser(Users user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetende);
            message.setTo(user.getEmail());
            message.setSubject("Seja bem vindo: " + user.getUsername());
            message.setText("Seu email de acesso: " + user.getEmail() + "\n Sua senha é: " + user.getPassword()
                    + "\n Agora poderá realizar comentarios apartir do seu nome de usuario. ");
            message.setText("Acesse o link para validar sua conta: http://localhost:8080/auth/" + user.getId() + "/"
                    + user.getUsername());
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}