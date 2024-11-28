package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dao.UserDAO;
import lk.ijse.cropmanagement.dto.impl.UserDTO;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.entity.impl.UserEntity;
import lk.ijse.cropmanagement.exception.UserNotFoundException;
import lk.ijse.cropmanagement.service.StaffService;
import lk.ijse.cropmanagement.service.UserService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private StaffService staffService;

    @Autowired
    private Mapping mapping;

    @Autowired
    private AppUtill appUtill;
    @Override
    public void saveUser(UserDTO userDTO) {
        // Generate User ID
        String generatedUserId = appUtill.generateId("USER");
        userDTO.setUserId(generatedUserId);

        // Save the UserEntity first
        UserEntity userEntity = mapping.toUserEntity(userDTO);
        UserEntity savedUser = userDAO.save(userEntity);
        if (savedUser == null) {
            throw new RuntimeException("User not saved");
        }

        // Create the Staff entity with only ID, role, and email
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setStaffId(appUtill.generateId("STAFF"));  // Auto-generate staff ID
        staffEntity.setRole(userDTO.getRole());  // Set the role from the UserDTO
        staffEntity.setEmail(userDTO.getEmail()); // Set the email

        // Save the Staff entity
        StaffEntity savedStaff = staffDAO.save(staffEntity);
        if (savedStaff == null) {
            throw new RuntimeException("Staff not saved");
        }

        // Associate the saved Staff entity with the User entity
        userEntity.setStaff(savedStaff);

        // Update the UserEntity with the associated StaffEntity
        userDAO.save(userEntity);
    }

    @Override
    public UserDetailsService userDetailService() {
        return userName ->
                userDAO.findByEmail(userName)
                        .orElseThrow(()->new UserNotFoundException("User Not Found"));
    }
    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Optional<UserEntity> byEmail = userDAO.findByEmail(email);

        return byEmail.map(mapping::toUserDTO);
    }



}
