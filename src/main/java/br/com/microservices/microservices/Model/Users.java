package br.com.microservices.microservices.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import br.com.microservices.microservices.Model.DTO.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "tb_users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String username;
    String password;
    String email;
    Role authenticated;

    @OneToMany(mappedBy = "userComent", cascade = CascadeType.ALL)
    Set<Comment> comment = new HashSet<>();

    public Users(String username, String password, String email, Role authenticated) {
        this.authenticated = authenticated;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Role getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Role authenticated) {
        this.authenticated = authenticated;
    }

    public Users() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

}
