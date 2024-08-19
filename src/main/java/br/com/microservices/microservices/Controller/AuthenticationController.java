package br.com.microservices.microservices.Controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.microservices.microservices.Configuration.Security.TokenService;
import br.com.microservices.microservices.Model.Users;
import br.com.microservices.microservices.Model.DTO.LoginResponseDTO;
import br.com.microservices.microservices.Model.DTO.Role;
import br.com.microservices.microservices.Model.DTO.UserDTO;
import br.com.microservices.microservices.Repository.UserRepository;
import br.com.microservices.microservices.Services.SendEmailService;
import br.com.microservices.microservices.Services.UserService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    TokenService tokenService;
    SendEmailService sendEmailService;
    PasswordEncoder passwordEncoder;
    UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenService tokenService, SendEmailService sendEmailService, PasswordEncoder passwordEncoder,
            UserService userService) {
        this.userService = userService;
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
        if (passwordEncoder.matches(userDTO.password(), user.getPassword())) {
            String token = this.tokenService.generatedToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token, userDTO.username()));
        }
        return ResponseEntity.badRequest().body("Need login between Postman or same ap");
    }

    @PostMapping("/singup")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        if (this.userRepository.findByUsername(userDTO.username()).isPresent())
            return ResponseEntity.badRequest().build();
        System.out.println(userDTO.password());
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        Users u1 = new Users(userDTO.username(), encryptedPassword, userDTO.email(), Role.NotAuthenticated);
        this.userRepository.save(u1);
        sendEmailService.sendEmailNewUser(u1);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{username}/{id}")
    public ModelAndView validUserEmail(@PathVariable String username, @PathVariable UUID id) {
        Users users = userRepository.findByUsername(username).orElseThrow();
        userService.updateEmailUser(users.getUsername(), users.getId());

        return new ModelAndView("redirect:/home");
    }

}