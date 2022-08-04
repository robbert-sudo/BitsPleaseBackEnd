package bitspleaseApp.controller;

import bitspleaseApp.dto.request.UserPostRequest;
import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.dto.response.UserRateResponse;
import bitspleaseApp.model.User;
import bitspleaseApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;


@RestController
@RequestMapping("")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/user")
    public ResponseEntity<Object> createUser(@RequestBody UserPostRequest userPostRequest) {

        String newUsername = userService.create(userPostRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/user/delete/{user_id}")
    public ResponseEntity disableUser(@PathVariable("user_id") long user_id) {
        userService.disableUser(user_id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/user/{user_id}")
    public ResponseEntity<Object> updateUser(@PathVariable("user_id") long user_id, @RequestBody UserPostRequest userPostRequest) {
        userService.updateUser(user_id, userPostRequest);
        return ResponseEntity.ok("user aangepast.");
    }

    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        Set<UserDetailsResponse> userDetailsResponses = userService.findAll();
        return ResponseEntity.ok(userDetailsResponses);
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username) {
        UserDetailsResponse userDetailsResponse = userService.getUser(username);
        return ResponseEntity.ok(userDetailsResponse);
    }

    @GetMapping(value = "user/id/{user_id}")
    public ResponseEntity getUserById(@PathVariable("user_id") long user_id) {
        UserRateResponse userRateResponse = userService.getUserById(user_id);
        return ResponseEntity.ok(userRateResponse);
    }


}
