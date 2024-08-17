package br.com.microservices.microservices.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.microservices.Configuration.Security.TokenService;
import br.com.microservices.microservices.Model.Users;
import br.com.microservices.microservices.Model.DTO.LoginResponseDTO;
import br.com.microservices.microservices.Model.DTO.UserDTO;
import br.com.microservices.microservices.Repository.UserRepository;
import br.com.microservices.microservices.Services.SendEmailService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    TokenService tokenService;
    SendEmailService sendEmailService;
    PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenService tokenService, SendEmailService sendEmailService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/singin")
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
        Users user = this.userRepository.findByUsername(userDTO.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(userDTO.password(), user.getPassword())){
            String token = this.tokenService.generatedToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token, userDTO.username()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/singup")
    public ResponseEntity register(@RequestBody UserDTO userDTO) {
        if (this.userRepository.findByUsername(userDTO.username()).isPresent())
            return ResponseEntity.badRequest().build();
        System.out.println(userDTO.password());
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        Users u1 = new Users(userDTO.username(), encryptedPassword, userDTO.email());
        this.userRepository.save(u1);
        sendEmailService.sendEmailNewUser(u1);
        return ResponseEntity.ok().build();
    }

}