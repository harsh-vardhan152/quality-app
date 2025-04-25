package com.harshvardhan.quality_app.service;

import com.harshvardhan.quality_app.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {


    private User testUser;

    @BeforeEach
    void StepUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("Test");
        testUser.setEmail("test@gmail.com");
        testUser.setPassword("Test");
    }

    @Test
    void createUser_Success() {
        // TODO document why this method is empty
    }

}
