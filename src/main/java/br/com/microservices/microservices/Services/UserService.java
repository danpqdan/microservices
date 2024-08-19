package br.com.microservices.microservices.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.microservices.microservices.Model.Users;
import br.com.microservices.microservices.Model.DTO.Role;
import br.com.microservices.microservices.Model.DTO.UserDTO;
import br.com.microservices.microservices.Repository.CommentRepository;
import br.com.microservices.microservices.Repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    UserRepository userRepository;
    CommentRepository commentRepository;
    SendEmailService sendEmailService;

    public UserService(UserRepository userRepository, CommentRepository commentRepository,
            SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Users saveUser(UserDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        userRepository.save(user);
        // sendEmailService.sendEmailNewUser(user);
        return user;
    }

    public List<Users> listUsers() {
        return userRepository.findAll();
    }

    public Users findOne(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.username()).get();
    }

    public Users updateEmailUser(String username, UUID id) {
        Users u1 = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        u1.setAuthenticated(Role.Authenticated);
        userRepository.saveAndFlush(u1);
        return u1;
    }

}
