package bitspleaseApp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Authority authority;
    private Authority authority1;
    private Authority authority2;


    @BeforeEach
    void setUp() {
        //arrange
        this.user = new User();
        this.user.setUsername("admin");
        this.user.setEmail("admin@admin.ad");
        this.user.setUser_id(1);
        this.user.setEnabled(true);
        this.user.setPassword("password");



        this.authority = new Authority();
        this.authority.setUser_id(1);
        this.authority.setAuthority("ROLE_USER");
        this.authority.setUsername("admin");

        this.authority1 = new Authority();
        this.authority1.setUser_id(1);
        this.authority1.setAuthority("ROLE_ADMIN");
        this.authority1.setUsername("admin");

        Set<Authority> authorities = new HashSet<>();


        authorities.add(authority);
        authorities.add(authority1);

        this.user.setAuthorities(authorities);





//
//        authorities.add(authority1);


//        this.user.setAuthorities(authorities);

//        this.user.addAuthority( "ROLE_USER");
//        this.user.addAuthority("ROLE_ADMIN");
    }

    @Test
    void testGetNameAndEmail() {
        //act
        String expectedNameAndEmail = "admin admin@admin.ad";
        String actualNameAndEmail = this.user.getNameAndEmail();
        //assert
        assertEquals(expectedNameAndEmail, actualNameAndEmail);
    }


    @Test
    void getUser_id() {
        //act
        long expectedId = 1;
        long actualId = this.user.getUser_id();
        //assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void setUser_id() {
        //act
        this.user.setUser_id(2);
        long expectedId = 2;
        long actualId = this.user.getUser_id();
        //assert
        assertEquals(expectedId, actualId);

    }

    @Test
    void getUsername() {
        //act
        String expectedUsername = "admin";
        String actualUsername = this.user.getUsername();
        //assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void setUsername() {
        //act
        this.user.setUsername("administrator");
        String expectedUsername = "administrator";
        String actualUsername = this.user.getUsername();
        //assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void getPassword() {
        //act
        String expectedPassword = "password";
        String actualPassword = this.user.getPassword();
        //assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setPassword() {
        //act
        this.user.setPassword("hallo");
        String expectedPassword = "hallo";
        String actualPassword = this.user.getPassword();
        //assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void isEnabled() {
        //act
        boolean expectedAnswer = true;
        boolean actualAnswer = this.user.isEnabled();
        //assert
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void setEnabled() {
        //act
        this.user.setEnabled(false);
        boolean expectedAnswer = false;
        boolean actualAnswer = this.user.isEnabled();
        //assert
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void getEmail() {
        //act
        String expectedEmail = "admin@admin.ad";
        String actualEmail = this.user.getEmail();
        //assert
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void setEmail() {
        //act
        this.user.setEmail("dirk@diggler.dug");
        String expectedEmail = "dirk@diggler.dug";
        String actualEmail = this.user.getEmail();
        //assert
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void getAuthorities() {
        //act
        Set<Authority> authorities = this.user.getAuthorities();
        long expectedSize = 2;
        long actualSize = authorities.size();
        //assert
        assertEquals(expectedSize, actualSize);

        //act
        String expectedRole = "ROLE_USER";
        String actualRole =  this.authority.getAuthority();
        //assert
        assertEquals(expectedRole, actualRole);

        //act
        String expectedRole2 = "ROLE_ADMIN";
        String actualRole2 = this.authority1.getAuthority();
        //assert
        assertEquals(expectedRole2, actualRole2);
//        Set<Authority> authorities1 = this.user.getAuthorities();
//        String auth = authorities1.toString();
//        String expectedauth = "ROLE_USER";
//        assertEquals(expectedauth, auth);


//        Set<Authority> authority = this.user.getAuthorities();
//
//        boolean expectedAuthority = true;
//        boolean actualAuthority = authority.Authorities.contains("ROLE_USER");
//        assertEquals(expectedAuthority, actualAuthority);
//
//        boolean expectedauth = false;
//        boolean actualauth = authority.contains("ROLE_ADMIN");
//        assertEquals(expectedauth, actualauth);
    }


    @Test
    void setAuthorities() {
        //act
        this.authority2 = new Authority();
        this.authority2.setUser_id(1);
        this.authority2.setAuthority("ROLE_SUPERUSER");
        this.authority2.setUsername("admin");



//        authorities.add(authority2);
//
//
//
//        this.user.setAuthorities(authority2);
    }

    @Test
    void addAuthority() {
    }

    @Test
    void testAddAuthority() {
    }
}