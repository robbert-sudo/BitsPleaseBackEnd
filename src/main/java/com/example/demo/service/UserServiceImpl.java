package com.example.demo.service;

import com.example.demo.dto.request.UserPostRequest;
import com.example.demo.dto.response.UserDetailsResponse;
import com.example.demo.dto.response.UserRateResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
//    stop gevonden users in een dto zonder wachtwoord zodat deze nooit de database verlaat.
    public Set<UserDetailsResponse> findAll() {
        long user_id = 0;
        String username = null;
        boolean enabled = false;
        String email = null;
        Set<Authority> authorities = null;
        Set<UserDetailsResponse> userDetailsResponses = new HashSet<>();

        Iterable<User> users = userRepository.findAll();
        for (User user : users) {

            user_id = user.getUser_id();
            username = user.getUsername();
            enabled = user.isEnabled();
            email = user.getEmail();
            authorities = user.getAuthorities();
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse(user_id, username, enabled, email, authorities);
            userDetailsResponses.add(userDetailsResponse);
        }
        return userDetailsResponses;
    }


    @Override
    public UserDetailsResponse getUser(String username1) {
        long user_id = 0;
        String username = null;
        boolean enabled = false;
        String email = null;
        Set<Authority> authorities = null;

        Optional<User> user = userRepository.findByUsername(username1);
        if (user.isPresent()) {
            user_id = user.get().getUser_id();
            username = user.get().getUsername();
            enabled = user.get().isEnabled();
            email = user.get().getEmail();
            authorities = user.get().getAuthorities();
        }

        return new UserDetailsResponse(user_id, username, enabled, email, authorities);
    }

    @Override
    public String create(UserPostRequest userPostRequest) {
        try {

            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());

            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(userPostRequest.getEmail());
            user.setEnabled(true);
            User savedUser = userRepository.save(user);
            savedUser.addAuthority("ROLE_USER");

            userRepository.save(savedUser);
            return savedUser.getUsername();
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    @Override
    public void delete(String username) {
        Optional<User> possibleUser = userRepository.findByUsername(username);
        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            userRepository.delete(user);
        }
    }

    @Override
    public UserRateResponse getUserById(long user_id) {
        String ratedUserName = null;
        try {
            Optional<User> user = userRepository.findById(user_id);
            if (user.isPresent()) {
                ratedUserName = user.get().username;

            }

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return new UserRateResponse(ratedUserName);
    }

    @Override
    public void disableUser(long user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(false);
            userRepository.save(user);
        }
        else {
            throw new UserNotFoundException();
        }
    }

    public Set<UserDetailsResponse> findAllByDisabled() {
        long user_id = 0;
        String username = null;
        boolean enabled = false;
        String email = null;
        Set<Authority> authorities = null;
        Set<UserDetailsResponse> userDetailsResponses = new HashSet<>();

        Iterable<User> users = userRepository.findAllByEnabled(false);
        for (User user : users) {
            user_id = user.getUser_id();
            username = user.getUsername();
            enabled = user.isEnabled();
            email = user.getEmail();
            authorities = user.getAuthorities();
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse(user_id, username, enabled, email, authorities);
            userDetailsResponses.add(userDetailsResponse);
        }
        return userDetailsResponses;
    }



    }




