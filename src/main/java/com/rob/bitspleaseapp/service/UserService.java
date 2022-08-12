package com.rob.bitspleaseapp.service;

import com.rob.bitspleaseapp.dto.request.UserEmailPatchRequest;
import com.rob.bitspleaseapp.dto.request.UserPostRequest;
import com.rob.bitspleaseapp.dto.response.UserDetailsResponse;
import com.rob.bitspleaseapp.dto.response.UserRateResponse;

import java.util.Set;


public interface UserService {

    public String create(UserPostRequest userPostRequest);

    void disableUser(long user_id);

   void updateUser(long user_id, UserEmailPatchRequest userEmailPatchRequest);

    public Set<UserDetailsResponse> findAll();

    public UserDetailsResponse getUser(String username);

    public UserRateResponse getUserById(long user_id);

}
