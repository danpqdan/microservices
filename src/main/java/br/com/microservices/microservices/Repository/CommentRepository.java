package br.com.microservices.microservices.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.microservices.microservices.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    
}
