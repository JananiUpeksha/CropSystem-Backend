package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dao.UserDAO;
import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import lk.ijse.cropmanagement.dto.impl.UserDTO;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.entity.impl.UserEntity;
import lk.ijse.cropmanagement.service.StaffService;
import lk.ijse.cropmanagement.service.UserService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /*@Override
    public void saveUser(UserDTO userDTO) {
        // Generate User ID
        String generatedUserId = appUtill.generateId("USER");
        userDTO.setUserId(generatedUserId);

        // Use the existing saveStaff method in StaffService
        if (userDTO.getStaff() != null) {
            staffService.saveStaff(userDTO.getStaff());
        } else {
            throw new RuntimeException("Staff details are required for user creation.");
        }

        // Convert UserDTO to UserEntity
        UserEntity userEntity = mapping.toUserEntity(userDTO);

        // Retrieve the saved StaffEntity by its ID and associate it with the UserEntity
        StaffDTO savedStaffDTO = userDTO.getStaff();
        StaffEntity staffEntity = staffDAO.findById(savedStaffDTO.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found after saving"));

        userEntity.setStaff(staffEntity);

        // Save the UserEntity
        UserEntity savedUser = userDAO.save(userEntity);
        if (savedUser == null) {
            throw new RuntimeException("User not saved");
        }
    }*/
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

        // Create the Staff entity with only ID and role
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setStaffId(appUtill.generateId("STAFF"));  // Auto-generate staff ID
        staffEntity.setRole(userDTO.getRole());  // Set the role from the UserDTO

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



}
