package com.stefane.article_manager.config;

import com.stefane.article_manager.model.Usuario;
import com.stefane.article_manager.model.UsuarioRole;
import com.stefane.article_manager.repository.UsuarioRepository;
import com.stefane.article_manager.repository.UsuarioRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            UsuarioRepository usuarioRepository,
            UsuarioRoleRepository roleRepository,
            PasswordEncoder encoder
    ) {
        return args -> {
            UsuarioRole aluno = roleRepository.findByName("ROLE_ALUNO")
                    .orElseGet(() -> roleRepository.save(new UsuarioRole("ROLE_ALUNO")));

            UsuarioRole professor = roleRepository.findByName("ROLE_PROFESSOR")
                    .orElseGet(() -> roleRepository.save(new UsuarioRole("ROLE_PROFESSOR")));

            UsuarioRole adm = roleRepository.findByName("ROLE_ADM")
                    .orElseGet(() -> roleRepository.save(new UsuarioRole("ROLE_ADM")));

            if (!usuarioRepository.existsByEmail("adm@email.com")) {
                Usuario admin = new Usuario(
                        "Administrador",
                        "adm@email.com",
                        encoder.encode("123456"),
                        adm
                );

                usuarioRepository.save(admin);
            }
        };
    }
}