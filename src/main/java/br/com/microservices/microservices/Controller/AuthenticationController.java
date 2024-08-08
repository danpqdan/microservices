package br.com.microservices.microservices.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.microservices.Configuration.Security.TokenService;
import br.com.microservices.microservices.Model.User;
import br.com.microservices.microservices.Model.DTO.LoginResponseDTO;
import br.com.microservices.microservices.Model.DTO.UserDTO;
import br.com.microservices.microservices.Repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/singin")
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generatedToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/singup")
    public ResponseEntity register(@RequestBody UserDTO userDTO) {
        if (this.userRepository.findByUsername(userDTO.username()) != null)
            return ResponseEntity.badRequest().build();
        System.out.println(userDTO.password());
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        User u1 = new User(userDTO.username(), encryptedPassword, userDTO.email(), null);
        User user = new User(userDTO.username(),encryptedPassword, userDTO.email(), userDTO.authorization());
        this.userRepository.save(u1);

        return ResponseEntity.ok().build();

    }
}