package br.com.microservices.microservices.Model.DTO;

import java.util.UUID;

public record CommentDTO(
        UUID commentID,
        String contentComment,
        String username

) {

}
