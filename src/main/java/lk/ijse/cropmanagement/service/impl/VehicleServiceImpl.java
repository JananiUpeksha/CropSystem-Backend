package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dao.VehicleDAO;
import lk.ijse.cropmanagement.dto.impl.VehicleDTO;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.entity.impl.VehicleEntity;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.VehicleService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private AppUtill appUtill;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        String generatedVehicleId = appUtill.generateId("VEHICLE");
        vehicleDTO.setVehicleId(generatedVehicleId);
        VehicleEntity vehicleEntity = mapping.toVehicleEntity(vehicleDTO);

        if (vehicleDTO.getStaffId() != null) {
            StaffEntity staffEntity = staffDAO.findById(vehicleDTO.getStaffId())
                    .orElseThrow(() -> new DataPersistException("Staff not found"));
            vehicleEntity.setStaff(staffEntity);
        }

        VehicleEntity saveVehicle = vehicleDAO.save(vehicleEntity);
        if (saveVehicle == null) {
            throw new DataPersistException("Vehicle not saved");
        }

    }

    @Override
    public void updateVehicle(String id, VehicleDTO vehicleDTO) {
        VehicleEntity existingVehicle = vehicleDAO.findById(id)
                .orElseThrow(() -> new DataPersistException("Vehicle not found"));

        existingVehicle.setPlateNumber(vehicleDTO.getPlateNumber());
        existingVehicle.setCategory(vehicleDTO.getCategory());
        existingVehicle.setFuelType(vehicleDTO.getFuelType());
        existingVehicle.setStatus(vehicleDTO.getStatus());
        existingVehicle.setRemarks(vehicleDTO.getRemarks());

        if (vehicleDTO.getStaffId() != null) {
            StaffEntity staffEntity = staffDAO.findById(vehicleDTO.getStaffId())
                    .orElseThrow(() -> new DataPersistException("Staff not found"));
            existingVehicle.setStaff(staffEntity);
        } else {
            existingVehicle.setStaff(null);
        }

        vehicleDAO.save(existingVehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.asVehicleDTOList(vehicleDAO.findAll());
    }

    @Override
    public VehicleDTO getVehicleById(String id) {
        VehicleEntity vehicle = vehicleDAO.findById(id)
                .orElseThrow(() -> new DataPersistException("Vehicle not found"));
        return mapping.toVehicleDTO(vehicle);
    }

    @Override
    public void deleteField(String id) {
        if (!vehicleDAO.existsById(id)) {
            throw new DataPersistException("Vehicle not found");
        }
        vehicleDAO.deleteById(id);
    }


}
