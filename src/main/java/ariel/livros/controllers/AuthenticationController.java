package ariel.livros.controllers;

import ariel.livros.domain.user.User;
import ariel.livros.dto.auth.LoginResponseDTO;
import ariel.livros.dto.auth.RegisterDTO;
import ariel.livros.dto.auth.AuthenticationDTO;
import ariel.livros.repository.UserRepository;
import ariel.livros.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final UserRepository repository;
    private final TokenService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = manager.authenticate(usernamePassword);
        var token = service.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){
        if (repository.findByUsername(dto.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.username(), encryptedPassword, dto.role());

        repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
