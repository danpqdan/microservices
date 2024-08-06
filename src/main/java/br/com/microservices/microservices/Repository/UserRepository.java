package br.com.microservices.microservices.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.microservices.microservices.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    UserDetails findByUsername(String username);

}
