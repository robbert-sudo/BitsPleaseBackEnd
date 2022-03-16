package com.example.demo.controller;

import com.example.demo.dto.response.UserDetailsResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/deletedusers")
    public ResponseEntity GetDisabledUsers() {
        Set<UserDetailsResponse> userDetailsResponse = userService.findAllByDisabled();
        return ResponseEntity.ok(userDetailsResponse);
    }


   @DeleteMapping(value = "delete/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        userService.delete(username);
        return ResponseEntity.ok("User verwijderd!");
   }


}
