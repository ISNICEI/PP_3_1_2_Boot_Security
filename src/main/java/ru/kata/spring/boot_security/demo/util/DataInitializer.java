package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {

        if (roleRepository.findByName("ADMIN") == null) {
            Role adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("USER") == null) {
            Role userRole = new Role("USER");
            roleRepository.save(userRole);
        }

        // Проверяем существование пользователей
        if (userService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRoles(Set.of(
                    roleRepository.findByName("ADMIN"),
                    roleRepository.findByName("USER")
            ));
            userService.save(admin);
        }

        if (userService.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user");
            user.setRoles(Set.of(roleRepository.findByName("USER")));
            userService.save(user);
        }
    }
}
