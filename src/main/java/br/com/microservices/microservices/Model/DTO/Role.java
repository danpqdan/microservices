package br.com.microservices.microservices.Model.DTO;

public enum Role {
    Authenticated("authenticated"),
    NotAuthenticated("notAuthenticated");

    String authenticated;

    private Role(String authenticated) {
        this.authenticated = authenticated;
    }
}
