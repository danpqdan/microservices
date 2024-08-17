package br.com.microservices.microservices.Model.DTO;

import java.util.UUID;

public record UserDTO(
    String username,
    String password,
    String email,
    UUID userComment,
    String contentComment
) {

}
