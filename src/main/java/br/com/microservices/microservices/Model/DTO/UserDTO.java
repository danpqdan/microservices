package br.com.microservices.microservices.Model.DTO;

import java.util.UUID;

import br.com.microservices.microservices.Model.Enuns.AuthorityEnum;

public record UserDTO(
    String username,
    String password,
    String email,
    UUID userComment,
    String contentComment,
    AuthorityEnum authorization

) {

}
