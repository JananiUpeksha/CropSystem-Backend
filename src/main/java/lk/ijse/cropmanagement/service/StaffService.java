package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    StaffDTO getStaffById(String staffId);

    List<StaffDTO> getAllStaff();

    void deleteStaff(String staffId);

    void updateStaff(String staffId, StaffDTO staffDTO);

    StaffDTO findStaffById(String staffId);

    StaffDTO save(StaffDTO staffDTO);

    Optional<StaffDTO> findByEmail(String email);
}
