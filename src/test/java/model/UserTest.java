package model;

import bitspleaseApp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User();
        this.user.setUsername("admin");
        this.user.setEmail("admin@admin.ad");
    }

    @Test
    void testGetNameAndEmail() {
        String expectedNameAndEmail = "admin admin@admin.ad";
        String actualNameAndEmail = this.user.getNameAndEmail();
        assertEquals(expectedNameAndEmail, actualNameAndEmail);
    }
}