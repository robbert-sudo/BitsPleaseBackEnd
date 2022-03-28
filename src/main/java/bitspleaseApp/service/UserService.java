package bitspleaseApp.service;

import bitspleaseApp.dto.request.UserPostRequest;
import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.dto.response.UserRateResponse;

import java.util.Set;


public interface UserService {

    public Set<UserDetailsResponse> findAll();

    public UserDetailsResponse getUser(String username);

    public String create(UserPostRequest userPostRequest);

    void delete(String username);

    public UserRateResponse getUserById(long user_id);

    void disableUser(long user_id);

    Set<UserDetailsResponse> findAllByDisabled();
}
