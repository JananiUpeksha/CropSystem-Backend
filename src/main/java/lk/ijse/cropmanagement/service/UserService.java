package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    void saveUser(UserDTO userDTO);
    UserDetailsService userDetailService();

    Optional<UserDTO> findByEmail(String email);
}
