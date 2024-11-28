package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.UserDAO;
import lk.ijse.cropmanagement.dto.impl.UserDTO;
import lk.ijse.cropmanagement.entity.impl.UserEntity;
import lk.ijse.cropmanagement.exception.UserNotFoundException;
import lk.ijse.cropmanagement.security.JWTAuthResponse;
import lk.ijse.cropmanagement.security.SignIn;
import lk.ijse.cropmanagement.service.AuthService;
import lk.ijse.cropmanagement.service.JwtService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AppUtill appUtill;
    @Autowired
    private  UserDAO userDao;
    @Autowired
    private  Mapping mapping;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        var generatedToken = jwtService.generateToken(user);

        return JWTAuthResponse.builder().token(generatedToken).build();
    }


    //save user in db and issue a token
    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        //userDTO.setUserId(AppUtill.generateUserId());
        userDTO.setUserId(appUtill.generateId("USER"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //save user
        UserEntity user = mapping.toUserEntity(userDTO);
        System.out.println(user);
        UserEntity savedUser = userDao.save(user);
        // System.out.println(savedUser);
        //generate token
        var token = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(token).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        //extract username from existing token
        var userName= jwtService.extractUserName(accessToken);
        //check the user availability in the db
        var findUser=  userDao.findByEmail(userName)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        var refreshedToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshedToken).build();


    }

}
