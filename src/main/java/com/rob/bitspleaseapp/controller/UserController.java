package com.rob.bitspleaseapp.controller;

import com.rob.bitspleaseapp.dto.request.UserPostRequest;
import com.rob.bitspleaseapp.dto.response.UserDetailsResponse;
import com.rob.bitspleaseapp.dto.response.UserRateResponse;
import com.rob.bitspleaseapp.dto.request.UserEmailPatchRequest;
import com.rob.bitspleaseapp.service.UserService;
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
    public ResponseEntity<Object> updateUser(@PathVariable("user_id") long user_id, @RequestBody UserEmailPatchRequest userEmailPatchRequest) {
        userService.updateUser(user_id, userEmailPatchRequest);
        return ResponseEntity.ok("user aangepast.");
    }

    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        Set<UserDetailsResponse> userDetailsResponses = userService.findAll();
        return ResponseEntity.ok(userDetailsResponses);
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity getUserByUserName(@PathVariable("username") String username) {
        UserDetailsResponse userDetailsResponse = userService.getUserByUserName(username);
        return ResponseEntity.ok(userDetailsResponse);
    }

    @GetMapping(value = "user/id/{user_id}")
    public ResponseEntity getUserById(@PathVariable("user_id") long user_id) {
        UserRateResponse userRateResponse = userService.getUserById(user_id);
        return ResponseEntity.ok(userRateResponse);
    }


}
