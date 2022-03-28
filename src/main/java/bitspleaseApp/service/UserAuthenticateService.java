package bitspleaseApp.service;

import bitspleaseApp.dto.request.AuthenticationRequest;
import bitspleaseApp.dto.response.AuthenticationResponse;

public interface UserAuthenticateService {


    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
}
