package ariel.livros.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private String ttpe;

    UserRole(String ttpe) {
        this.ttpe = ttpe;
    }
}
