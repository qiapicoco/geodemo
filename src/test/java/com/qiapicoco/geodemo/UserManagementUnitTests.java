package com.qiapicoco.geodemo;

import com.qiapicoco.geodemo.entity.User;
import com.qiapicoco.geodemo.repository.UserRepository;
import com.qiapicoco.geodemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserManagementUnitTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testUserRegistrationPasswordEncryption() {
        User user = new User();
        user.setUsername("testUser");
        String rawPassword = "testPassword";
        user.setPassword(rawPassword);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser(user);

        assertTrue(passwordEncoder.matches(rawPassword, registeredUser.getPassword()));
    }

    @Test
    public void testUserLoginAuthentication() {
        User user = new User();
        user.setUsername("testUser");
        String rawPassword = "testPassword";
        user.setPassword(passwordEncoder.encode(rawPassword));

        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        Optional<User> authenticatedUser = userService.authenticateUser("testUser", rawPassword);

        assertTrue(authenticatedUser.isPresent());
    }

    @Test
    public void testUserGetById() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(1);

        assertTrue(retrievedUser.isPresent());
        assertEquals("testUser", retrievedUser.get().getUsername());
    }

    @Test
    public void testUserUpdate() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setEmail("oldEmail@example.com");

        User updatedUser = new User();
        updatedUser.setEmail("newEmail@example.com");

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(updatedUser);

        assertEquals("newEmail@example.com", result.getEmail());
    }
}