package model;

import bitspleaseApp.model.Authority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorityTest {

    private Authority authority;

    @BeforeEach
    void setUp() {
        this.authority = new Authority();
        this.authority.setUser_id(1);
        this.authority.setAuthority("ROLE_USER");
        this.authority.setUsername("admin");
    }

    @Test
    void testGetUser_id() {
        long expectedUserId = 1;
        long actualUserId = this.authority.getUser_id();
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testGetAuthority() {
        String expectedAuthority = "ROLE_USER";
        String actualAthority = this.authority.getAuthority();
        assertEquals(expectedAuthority, actualAthority);
    }

    @Test
    void testGetUsername() {
        String expectedUsername = "admin";
        String actualUsername = this.authority.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testSetUser_id() {
        this.authority.setUser_id(2);
        long expectedUserId = 2;
        long actualUserId = this.authority.getUser_id();
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testSetAuthority() {
        this.authority.setAuthority("ROLE_ADMIN");
        String expectedAuthority = "ROLE_ADMIN";
        String actualAuthority = this.authority.getAuthority();
        assertEquals(expectedAuthority, actualAuthority);
    }

    @Test
    void testSetUsername() {
        this.authority.setUsername("administrator");
        String expectedUsername = "administrator";
        String actualUsername = this.authority.getUsername();
        assertEquals(expectedUsername, actualUsername );

    }

}
