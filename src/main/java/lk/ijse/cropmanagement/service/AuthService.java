package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.UserDTO;
import lk.ijse.cropmanagement.security.JWTAuthResponse;
import lk.ijse.cropmanagement.security.SignIn;

public interface AuthService {

    JWTAuthResponse signIn(SignIn signIn);

    //save user in db and issue a token
    JWTAuthResponse signUp(UserDTO userDTO);

    JWTAuthResponse refreshToken(String accessToken);
}
