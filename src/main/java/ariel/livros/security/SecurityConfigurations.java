package ariel.livros.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/book/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/book/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/book").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/book/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/student/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/student").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/student/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/student/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/loan/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/loan").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/loan/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 }
