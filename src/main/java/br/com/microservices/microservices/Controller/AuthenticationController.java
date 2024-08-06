package br.com.microservices.microservices.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.microservices.Model.User;
import br.com.microservices.microservices.Model.DTO.UserDTO;
import br.com.microservices.microservices.Repository.UserRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager; 
        this.userRepository = userRepository;
    }

    @PostMapping("/singin")
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/singup")
    public ResponseEntity singup(@RequestBody UserDTO userDTO){
        if(this.userRepository.findByUsername(userDTO.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        User user = new User(userDTO.username(), userDTO.email(), encryptedPassword, userDTO.authorization());
        this.userRepository.save(user);

        return ResponseEntity.ok().build();

    }
}