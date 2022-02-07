package com.example.demo.controller;

import com.example.demo.dto.request.UserPostRequest;
import com.example.demo.dto.response.UserRateResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        Iterable<User> users;
        users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username) {
        Optional<User> user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "user/id/{user_id}")
    public ResponseEntity getUserById(@PathVariable("user_id") long user_id) {
        UserRateResponse userRateResponse = userService.getUserById(user_id);
        return ResponseEntity.ok(userRateResponse);
    }


    @PostMapping(value = "/user")
    public ResponseEntity<Object> createUser(@RequestBody UserPostRequest userPostRequest) {

        String newUsername = userService.create(userPostRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/user/{user_id}")
    public ResponseEntity<Object> disableUser(@PathVariable("user_id") long user_id) {
        userService.disableUser(user_id);
        return ResponseEntity.noContent().build();
    }

}
