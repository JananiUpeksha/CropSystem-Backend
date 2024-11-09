package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
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
        staffDTO.setStaffId(appUtill.generateId("STAFF"));

        // Log the staff ID generation
        System.out.println("Generated Staff ID: " + staffDTO.getStaffId());

        // Convert StaffDTO to StaffEntity using the Mapping class
        StaffEntity staffEntity = mapping.toStaffEntity(staffDTO);

        // Log the StaffEntity object before saving
        System.out.println("Converted StaffEntity: " + staffEntity);

        // Save the StaffEntity to the database using the DAO
        StaffEntity savedStaff = staffDAO.save(staffEntity);

        // If the entity was not saved, throw a custom exception
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved");
        }

        // Log successful saving
        System.out.println("Staff saved successfully: " + savedStaff);
    }


}
