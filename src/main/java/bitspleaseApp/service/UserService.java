package bitspleaseApp.service;

import bitspleaseApp.dto.request.UserPatchRequest;
import bitspleaseApp.dto.request.UserPostRequest;
import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.dto.response.UserRateResponse;
import bitspleaseApp.model.User;

import java.util.Set;


public interface UserService {

    public String create(UserPostRequest userPostRequest);

    void disableUser(long user_id);

   void updateUser(long user_id, UserPatchRequest userPatchRequest);

    public Set<UserDetailsResponse> findAll();

    public UserDetailsResponse getUser(String username);

    public UserRateResponse getUserById(long user_id);


//onderstaande functies worden alleen gebruikt in de admin controller
    Set<UserDetailsResponse> findAllByDisabled();

    void delete(String username);


}
