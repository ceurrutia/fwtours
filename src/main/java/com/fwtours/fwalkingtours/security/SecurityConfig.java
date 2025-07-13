package com.fwtours.fwalkingtours.security;

import com.fwtours.fwalkingtours.services.CustomUserDetailsService;
import com.fwtours.fwalkingtours.utils.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    // Constructor único que inyecta ambos beans
    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuarios/login", "/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/api/tours", "/tours").permitAll()

                        // EMPRESA
                        .requestMatchers("/empresa/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/api/empresas/*/tours").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.PUT, "/api/empresas/*/tours/*").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.DELETE, "/api/empresas/*/tours/*").hasRole("EMPRESA")

                        // ADMIN
                        .requestMatchers("/admin/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/admin/empresas/**").hasRole("ADMIN")
                        .requestMatchers("/admin/reservas/**").hasAnyRole("ADMIN", "EMPRESA", "CLIENTE")

                        // Cualquier otro request necesita estar autenticado
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            // Aquí chequeamos roles y redirigimos
                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
                            boolean isEmpresa = authentication.getAuthorities().stream()
                                    .anyMatch(r -> r.getAuthority().equals("ROLE_EMPRESA"));
                            boolean isCliente = authentication.getAuthorities().stream()
                                    .anyMatch(r -> r.getAuthority().equals("ROLE_CLIENTE"));

                            if (isAdmin) {
                                response.sendRedirect("/admin/usuarios");
                            } else if (isEmpresa) {
                                response.sendRedirect("/empresa/reservas");
                            } else if (isCliente) {
                                response.sendRedirect("/cliente/reservas");
                            } else {
                                response.sendRedirect("/login?error=true");
                            }
                        })
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
