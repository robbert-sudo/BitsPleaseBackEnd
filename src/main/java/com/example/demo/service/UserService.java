package com.example.demo.service;

import com.example.demo.dto.request.UserPostRequest;
import com.example.demo.dto.response.UserDetailsResponse;
import com.example.demo.dto.response.UserRateResponse;
import com.example.demo.model.User;

import java.util.Optional;


public interface UserService {

    public Iterable<User> findAll();

    public UserDetailsResponse getUser(String username);

    public String create(UserPostRequest userPostRequest);

    void delete(String username);

    public UserRateResponse getUserById(long user_id);

    void disableUser(long user_id);

    Iterable<User> findAllByDisabled();
}
