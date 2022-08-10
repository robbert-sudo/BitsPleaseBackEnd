package bitspleaseApp.service;

import bitspleaseApp.dto.response.UserDetailsResponse;

import java.util.Set;

public interface AdminService {


    Set<UserDetailsResponse> findAllByDisabled();

    void delete(String username);
}
