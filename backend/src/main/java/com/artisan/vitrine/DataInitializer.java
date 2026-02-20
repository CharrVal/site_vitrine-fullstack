package com.artisan.vitrine;

import com.artisan.vitrine.entity.Role;
import com.artisan.vitrine.entity.User;
import com.artisan.vitrine.repository.RoleRepository;
import com.artisan.vitrine.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initUsers(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            Role adminRole = roleRepository.findByLibelle("ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setLibelle("ADMIN");
                        return roleRepository.save(role);
                    });

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("pass12345"));
                admin.setRole(adminRole);

                userRepository.save(admin);
            }
        };
    }
}
