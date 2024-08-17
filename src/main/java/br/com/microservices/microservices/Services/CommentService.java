package br.com.microservices.microservices.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.microservices.microservices.Model.Comment;
import br.com.microservices.microservices.Model.DTO.CommentDTO;
import br.com.microservices.microservices.Repository.CommentRepository;
import br.com.microservices.microservices.Repository.UserRepository;

@Service
public class CommentService {

    UserRepository userRepository;
    CommentRepository commentRepository;

    public CommentService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public Comment newComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setuserComent(userRepository.findByUsername(commentDTO.username()).get());
        comment.setContentComment(commentDTO.contentComment());
        return commentRepository.save(comment);
    }

    public List<Comment> listComments() {
        List<Comment> lista = commentRepository.findAll();
        return lista;
    }

}
