package model;

import bitspleaseApp.model.Authority;
import bitspleaseApp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
//    private Authority authority;

    @BeforeEach
    void setUp() {
        this.user = new User();
        this.user.setUsername("admin");
        this.user.setEmail("admin@admin.ad");
        this.user.setUser_id(1);
        this.user.setEnabled(true);
        this.user.setPassword("password");

        this.user.addAuthority( "ROLE_USER");
//        this.user.addAuthority("ROLE_ADMIN");
    }

    @Test
    void testGetNameAndEmail() {
        String expectedNameAndEmail = "admin admin@admin.ad";
        String actualNameAndEmail = this.user.getNameAndEmail();
        assertEquals(expectedNameAndEmail, actualNameAndEmail);
    }


    @Test
    void getUser_id() {
        long expectedId = 1;
        long actualId = this.user.getUser_id();
        assertEquals(expectedId, actualId);
    }

    @Test
    void setUser_id() {
        this.user.setUser_id(2);
        long expectedId = 2;
        long actualId = this.user.getUser_id();
        assertEquals(expectedId, actualId);

    }

    @Test
    void getUsername() {
        String expectedUsername = "admin";
        String actualUsername = this.user.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void setUsername() {
        this.user.setUsername("administrator");
        String expectedUsername = "administrator";
        String actualUsername = this.user.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void getPassword() {
        String expectedPassword = "password";
        String actualPassword = this.user.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setPassword() {
        this.user.setPassword("hallo");
        String expectedPassword = "hallo";
        String actualPassword = this.user.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void isEnabled() {
        boolean expectedAnswer = true;
        boolean actualAnswer = this.user.isEnabled();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void setEnabled() {
        this.user.setEnabled(false);
        boolean expectedAnswer = false;
        boolean actualAnswer = this.user.isEnabled();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void getEmail() {
        String expectedEmail = "admin@admin.ad";
        String actualEmail = this.user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void setEmail() {
        this.user.setEmail("dirk@diggler.dug");
        String expectedEmail = "dirk@diggler.dug";
        String actualEmail = this.user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

//    @Test
//    void getAuthorities() {
//        Set<Authority> authorities= this.user.getAuthorities();
//        Set<Authority> expectedAuthorities = new HashSet<>();
//        assertEquals(expectedAuthorities, authorities);
////        Set<Authority> expectedAuthorities =
//
//    }
//
//
//    @Test
//    void setAuthorities() {
//    }
//
//    @Test
//    void addAuthority() {
//    }
//
//    @Test
//    void testAddAuthority() {
//    }
}