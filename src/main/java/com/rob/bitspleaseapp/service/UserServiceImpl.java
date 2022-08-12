package com.rob.bitspleaseapp.service;

import com.rob.bitspleaseapp.dto.request.UserEmailPatchRequest;
import com.rob.bitspleaseapp.dto.request.UserPostRequest;
import com.rob.bitspleaseapp.dto.response.UserDetailsResponse;
import com.rob.bitspleaseapp.dto.response.UserRateResponse;
import com.rob.bitspleaseapp.model.Authority;
import com.rob.bitspleaseapp.model.Game;
import com.rob.bitspleaseapp.model.User;

import com.rob.bitspleaseapp.exceptions.BadRequestException;
import com.rob.bitspleaseapp.exceptions.RecordNotFoundException;
import com.rob.bitspleaseapp.exceptions.UserNotFoundException;
import com.rob.bitspleaseapp.repository.UserRepository;
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
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl() {

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
    public void disableUser(long user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(false);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void updateUser(long user_id, UserEmailPatchRequest userEmailPatchRequest) {
        if (!userRepository.existsById(user_id)) throw new RecordNotFoundException();

        User existingUser = userRepository.findById(user_id).get();
        existingUser.setEmail(userEmailPatchRequest.getEmail());
        userRepository.save(existingUser);
    }


    @Override
//    stop gevonden users in een dto zonder wachtwoord zodat deze nooit de backend verlaat.
    public Set<UserDetailsResponse> findAll() {
        long user_id = 0;
        String username = null;
        boolean enabled = false;
        String email = null;
        Set<Authority> authorities = null;
        Set<Game> games = null;
        Set<UserDetailsResponse> userDetailsResponses = new HashSet<>();

        Iterable<User> users = userRepository.findAll();
        for (User user : users) {

            user_id = user.getUser_id();
            username = user.getUsername();
            enabled = user.isEnabled();
            email = user.getEmail();
            authorities = user.getAuthorities();
            games = user.getGames();
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse(user_id, username, enabled, email, authorities, games);
            userDetailsResponses.add(userDetailsResponse);
        }
        return userDetailsResponses;
    }


    @Override
    public UserDetailsResponse getUserByUserName(String username1) {
        long user_id = 0;
        String username = null;
        boolean enabled = false;
        String email = null;
        Set<Authority> authorities = null;
        Set<Game> games = null;

        try {
        Optional<User> user = userRepository.findByUsername(username1);
        if (user.isPresent()) {
            user_id = user.get().getUser_id();
            username = user.get().getUsername();
            enabled = user.get().isEnabled();
            email = user.get().getEmail();
            authorities = user.get().getAuthorities();
            games = user.get().getGames();
        }
        else {
            throw new UserNotFoundException();
       }
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return new UserDetailsResponse(user_id, username, enabled, email, authorities, games );
    }


    @Override
    public UserRateResponse getUserById(long user_id) {
        String ratedUserName = null;
        try {
            Optional<User> user = userRepository.findById(user_id);
            if (user.isPresent()) {
                ratedUserName = user.get().username;

            } else {
                throw new UserNotFoundException();
            }
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return new UserRateResponse(ratedUserName);
    }

}




