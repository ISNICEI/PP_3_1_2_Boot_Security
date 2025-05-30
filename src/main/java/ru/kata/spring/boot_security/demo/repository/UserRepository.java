package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Поиск пользователя по логину
    User findByEmail(String email);      // Опционально, если есть email
}