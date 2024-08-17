package br.com.microservices.microservices.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Repository;

import br.com.microservices.microservices.Model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    
    Optional<Users> findByUsername(String nome);
    Users findByEmail(String username);

}
