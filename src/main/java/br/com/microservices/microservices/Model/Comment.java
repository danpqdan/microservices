
package br.com.microservices.microservices.Model;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String contentComment;

    @ManyToOne()
    @JoinColumn(name = "comment_id", nullable = false)
    Users userComent;
    

    public UUID getId() {
        return id;
    }

    public Comment() {
    }

    public Comment(String contentComment, Users userComent) {
        this.contentComment = contentComment;
        this.userComent = userComent;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public Users getuserComent(Users user) {
        return userComent;
    }

    public void setuserComent(Users user) {
        this.userComent = user ;
    }


 

}
