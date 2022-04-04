package bitspleaseApp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorityTest {

    private Authority authority;

    @BeforeEach
    void setUp() {
        //arrange
        this.authority = new Authority();
        this.authority.setUser_id(1);
        this.authority.setAuthority("ROLE_USER");
        this.authority.setUsername("admin");
    }

    @Test
    void testGetUser_id() {
        //act
        long expectedUserId = 1;
        long actualUserId = this.authority.getUser_id();
        //assert
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testGetAuthority() {
        //act
        String expectedAuthority = "ROLE_USER";
        String actualAuthority = this.authority.getAuthority();
        //assert
        assertEquals(expectedAuthority, actualAuthority);
    }

    @Test
    void testGetUsername() {
        //act
        String expectedUsername = "admin";
        String actualUsername = this.authority.getUsername();
        //assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testSetUser_id() {
        //act
        this.authority.setUser_id(2);
        long expectedUserId = 2;
        long actualUserId = this.authority.getUser_id();
        //assert
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testSetAuthority() {
        //act
        this.authority.setAuthority("ROLE_ADMIN");
        String expectedAuthority = "ROLE_ADMIN";
        String actualAuthority = this.authority.getAuthority();
        //assert
        assertEquals(expectedAuthority, actualAuthority);
    }

    @Test
    void testSetUsername() {
        //act
        this.authority.setUsername("administrator");
        String expectedUsername = "administrator";
        String actualUsername = this.authority.getUsername();
        //assert
        assertEquals(expectedUsername, actualUsername );

    }

    @Test
    void testAuthority() {
        //act
        Authority authority = new Authority(2, "ROLE_USER", "user");
        long expectedUserId = 2;
        String expectedAuthority = "ROLE_USER";
        String expectedUsername = "user";
        long actualUserId = authority.getUser_id();
        String actualAuthority = authority.getAuthority();
        String actualUsername = authority.getUsername();
        //assert
        assertEquals(expectedUserId, actualUserId);
        assertEquals(expectedAuthority, actualAuthority);
        assertEquals(expectedUsername, actualUsername);


    }


}