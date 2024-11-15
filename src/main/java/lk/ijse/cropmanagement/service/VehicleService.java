package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleDTO getVehicleById(String id);

    void deleteField(String id);

    void updateVehicle(String id, VehicleDTO vehicleDTO);
}
