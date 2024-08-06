package br.com.microservices.microservices.Model.Enuns;

public enum AuthorityEnum {
    USERAUTHORIZED("user_authorized"),
    USERNOTAUTHORIZED("user_not_authorized");

    String authorization;

    private AuthorityEnum(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    
}
