package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    StaffDTO getStaffById(String staffId);

    List<StaffDTO> getAllStaff();

    void deleteStaff(String staffId);

    void updateStaff(String staffId, StaffDTO staffDTO);
}
