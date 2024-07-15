package br.com.microservices.microservices.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DomainController {

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok()
                .body("Seja bem vindo a area inicial. Acesse localhost:8080/swagger-ui/index.html para mais informações");
    }

}
