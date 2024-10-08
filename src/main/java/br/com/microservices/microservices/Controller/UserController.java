package br.com.microservices.microservices.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.microservices.Model.Comment;
import br.com.microservices.microservices.Model.Users;
import br.com.microservices.microservices.Model.DTO.CommentDTO;
import br.com.microservices.microservices.Model.DTO.UserDTO;
import br.com.microservices.microservices.Repository.UserRepository;
import br.com.microservices.microservices.Services.CommentService;
import br.com.microservices.microservices.Services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final CommentService commentService;

    public UserController(UserService userService, CommentService commentService,
            UserRepository userRepository) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> comments(@RequestBody CommentDTO commentDTO) {
        commentService.newComment(commentDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> comments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.listComments());
    }

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @GetMapping("/one")
    public ResponseEntity<Users> getOne(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.findOne(userDTO));
    }

}
