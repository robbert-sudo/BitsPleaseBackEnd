package com.rob.bitspleaseapp.service;

import com.rob.bitspleaseapp.dto.request.AuthenticationRequest;
import com.rob.bitspleaseapp.dto.response.AuthenticationResponse;

public interface UserAuthenticateService {


    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
}
