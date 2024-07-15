package br.com.microservices.microservices.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.microservices.microservices.Model.User;
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

    public void sendEmailNewUser(User user) {
        User user1 = userRepository.findById(user.getId()).get();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetende);
            message.setTo(user1.getEmail());
            message.setSubject("Seja bem vindo: " + user1.getUsername());
            message.setText("Seu email de acesso: " + user1.getEmail() + "\n Sua senha é: " + user1.getPassword()
                    + "\n Agora poderá realizar comentarios apartir do seu nome de usuario. ");
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}