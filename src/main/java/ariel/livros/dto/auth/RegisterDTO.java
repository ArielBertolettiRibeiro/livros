package ariel.livros.dto.auth;

import ariel.livros.domain.enums.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
