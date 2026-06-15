package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder; // ✅ Añadido: UserServiceImpl lo necesita

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Carlos Valenciano");
        user.setEmail("carlos@example.com");
        user.setPassword("password123");
        user.setRole("USER");
    }

    @Test
    void getUser_deberiaRetornarUsuarioCuandoExiste() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUser(1L);

        assertEquals(1L, result.getId());
        verify(userRepository).findById(1L);
    }

    @Test
    void getUser_deberiaLanzarExcepcionCuandoNoExiste() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUser(99L));
        verify(userRepository).findById(99L);
    }

    @Test
    void getAllUsers_deberiaRetornarListaDeUsuarios() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void saveUser_deberiaGuardarConPasswordCifrada() {
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User toSave = new User();
        toSave.setName("Carlos");
        toSave.setEmail("carlos@example.com");
        toSave.setPassword("password123");
        toSave.setRole("USER");

        userService.saveUser(toSave);

        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(toSave);
    }

    @Test
    void deleteUser_deberiaLlamarDeleteById() {
        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }
}