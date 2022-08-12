package com.rob.bitspleaseapp.service;

import com.rob.bitspleaseapp.dto.response.UserDetailsResponse;

import java.util.Set;

public interface AdminService {

    Set<UserDetailsResponse> findAllByDisabled();

    void delete(String username);
}
