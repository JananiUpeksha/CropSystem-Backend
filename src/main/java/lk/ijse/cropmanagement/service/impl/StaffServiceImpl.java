package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.FieldDAO;
import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dao.UserDAO;
import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.entity.impl.UserEntity;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.StaffService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private Mapping mapping;

    @Autowired
    private AppUtill appUtill;  // AppUtill to generate the ID

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        // Log the incoming StaffDTO
        System.out.println("Received StaffDTO: " + staffDTO);

        // Generate the Staff ID dynamically using the AppUtill
        String generatedStaffId = appUtill.generateId("STAFF");
        staffDTO.setStaffId(generatedStaffId);

        // Log the staff ID generation
        System.out.println("Generated Staff ID: " + generatedStaffId);

        // Convert StaffDTO to StaffEntity using the Mapping class
        StaffEntity staffEntity = mapping.toStaffEntity(staffDTO);

        // Log the StaffEntity object before saving
        System.out.println("Converted StaffEntity: " + staffEntity);

        // Ensure the staffId is properly set before persisting
        if (staffEntity.getStaffId() == null || staffEntity.getStaffId().isEmpty()) {
            throw new DataPersistException("Staff ID is missing.");
        }

        // Save the StaffEntity to the database using the DAO
        StaffEntity savedStaff = staffDAO.save(staffEntity);

        // If the entity was not saved, throw a custom exception
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved");
        }

        // Log successful saving
        System.out.println("Staff saved successfully: " + savedStaff);
    }

    @Override
    public StaffDTO getStaffById(String staffId) {
        Optional<StaffEntity> staffEntity = staffDAO.findById(staffId);
        if (!staffEntity.isPresent()) {
            throw new RuntimeException("Staff not found for ID: " + staffId);
        }
        return mapping.toStaffDTO(staffEntity.get());
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffDAO.findAll().stream()
                .map(mapping::toStaffDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> staffEntity = staffDAO.findById(staffId);
        if (!staffEntity.isPresent()) {
            throw new RuntimeException("Staff not found for ID: " + staffId);
        }
        staffDAO.deleteById(staffId);
        if (!staffDAO.existsById(staffId)) {
            throw new RuntimeException("Log not found for ID: " + staffId);
        }
        staffDAO.deleteById(staffId);
    }
   // @Override
   /* public void deleteStaff(String staffId) {
        // Check if staff exists
        if (!staffDAO.existsById(staffId)) {
            throw new RuntimeException("Staff not found for ID: " + staffId);
        }

        // Check if staff is associated with any fields
        List<FieldEntity> associatedFields = fieldDAO.findByStaffId(staffId);
        if (!associatedFields.isEmpty()) {
            throw new RuntimeException("Cannot delete staff with active field associations.");
        }

        // Handle user association
        List<UserEntity> associatedUsers = userDAO.findByStaffId(staffId);
        for (UserEntity user : associatedUsers) {
            user.setStaff(null); // Remove the association
            userDAO.save(user);  // Save the updated user record
        }

        // Once all associations are handled, delete the staff record
        staffDAO.deleteById(staffId);
    }*/



    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> staffEntity = staffDAO.findById(staffId);
        if (!staffEntity.isPresent()) {
            throw new RuntimeException("Staff not found for ID: " + staffId);
        }
        StaffEntity existingStaff = staffEntity.get();
        existingStaff.setFirstName(staffDTO.getFirstName());
        existingStaff.setLastName(staffDTO.getLastName());
        existingStaff.setEmail(staffDTO.getEmail());
        existingStaff.setDob(staffDTO.getDob());
        existingStaff.setAddress(staffDTO.getAddress());
        existingStaff.setContact(staffDTO.getContact());
        existingStaff.setJoinDate(staffDTO.getJoinDate());
        existingStaff.setRole(staffDTO.getRole());
        staffDAO.save(existingStaff);
    }

    @Override
    public StaffDTO findStaffById(String staffId) {
        // Use staffDAO to find the staff entity by staffId
        StaffEntity staffEntity = staffDAO.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + staffId)); // Throw an exception if not found

        // Convert StaffEntity to StaffDTO using the mapping class
        return mapping.toStaffDTO(staffEntity);
    }

    @Override
    public StaffDTO save(StaffDTO staffDTO) {
        // Log the incoming StaffDTO
        System.out.println("Received StaffDTO: " + staffDTO);

        // Generate the Staff ID dynamically using the AppUtill
        String generatedStaffId = appUtill.generateId("STAFF");
        staffDTO.setStaffId(generatedStaffId);

        // Log the staff ID generation
        System.out.println("Generated Staff ID: " + generatedStaffId);

        // Convert StaffDTO to StaffEntity using the Mapping class
        StaffEntity staffEntity = mapping.toStaffEntity(staffDTO);

        // Log the StaffEntity object before saving
        System.out.println("Converted StaffEntity: " + staffEntity);

        // Ensure the staffId is properly set before persisting
        if (staffEntity.getStaffId() == null || staffEntity.getStaffId().isEmpty()) {
            throw new DataPersistException("Staff ID is missing.");
        }

        // Save the StaffEntity to the database using the DAO
        StaffEntity savedStaff = staffDAO.save(staffEntity);

        // If the entity was not saved, throw a custom exception
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved");
        }

        // Log successful saving
        System.out.println("Staff saved successfully: " + savedStaff);

        // Convert the saved StaffEntity back to a StaffDTO and return it
        return mapping.toStaffDTO(savedStaff);
    }


    @Override
    public Optional<StaffDTO> findByEmail(String email) {
        Optional<StaffEntity> byEmail = staffDAO.findByEmail(email);

        return byEmail.map(mapping::toStaffDTO);
    }


}
