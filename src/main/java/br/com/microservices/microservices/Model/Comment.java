
package br.com.microservices.microservices.Model;

import java.util.UUID;

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
    User userComent;

    public UUID getId() {
        return id;
    }

    public Comment() {
    }

    public Comment(String contentComment, User userComent) {
        this.contentComment = contentComment;
        this.userComent = userComent;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public User getuserComent(User user) {
        return userComent;
    }

    public void setuserComent(User userComent) {
        this.userComent = userComent;
    }


 

}
