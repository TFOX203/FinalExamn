package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// ✅ Se elimina @AutoConfigureTestDatabase(replace=NONE) para usar H2 en memoria
@DataJpaTest
@TestPropertySource(properties = {
	    "spring.sql.init.mode=never",
	    "spring.jpa.hibernate.ddl-auto=create-drop",
	    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
	})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        user = new User();
        user.setName("Carlos Valenciano");
        user.setEmail("carlos@example.com");
        user.setPassword("password123"); // ✅ Campo obligatorio añadido
        user.setRole("USER");            // ✅ Campo obligatorio añadido
    }

    @Test
    void shouldSaveUser() {
        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Carlos Valenciano");
    }

    @Test
    void shouldFindUserById() {
        User saved = userRepository.save(user);

        Optional<User> found = userRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("carlos@example.com");
    }

    @Test
    void shouldFindAllUsers() {
        userRepository.save(user);

        User otro = new User();
        otro.setName("Ana García");
        otro.setEmail("ana@example.com");
        otro.setPassword("password456"); // ✅ Campo obligatorio añadido
        otro.setRole("USER");            // ✅ Campo obligatorio añadido
        userRepository.save(otro);

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(2);
    }

    @Test
    void shouldDeleteUser() {
        User saved = userRepository.save(user);

        userRepository.deleteById(saved.getId());

        Optional<User> deleted = userRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void shouldUpdateUser() {
        User saved = userRepository.save(user);

        saved.setName("Carlos Actualizado");
        userRepository.save(saved);

        Optional<User> updated = userRepository.findById(saved.getId());
        assertThat(updated.get().getName()).isEqualTo("Carlos Actualizado");
    }

    @Test
    void shouldFindUserByEmail() {
        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("carlos@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Carlos Valenciano");
    }

    @Test
    void shouldReturnTrueWhenEmailExists() {
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("carlos@example.com");

        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalseWhenEmailNotExists() {
        boolean exists = userRepository.existsByEmail("noexiste@example.com");

        assertThat(exists).isFalse();
    }
}