package com.example.MarketplaceApplication.java;

import com.example.MarketplaceApplication.java.marketplace.entity.UserEntity;
import com.example.MarketplaceApplication.java.marketplace.repository.UserRepository;
import com.example.MarketplaceApplication.java.marketplace.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.client.ExpectedCount.times;
import static scala.Option.when;

public class TestMarket {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity createdUser = userService.createUser(user);

        assertEquals("testUser", createdUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testCreateUser_Error() {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");

        when(userRepository.save(any(UserEntity.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });

        assertEquals("Database error", exception.getMessage());
        verify(userRepository, times(1)).save(user);
    }
}
