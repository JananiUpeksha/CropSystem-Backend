package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipment();

    EquipmentDTO getEquipmentById(String id);

    EquipmentDTO updateEquipment(String id, EquipmentDTO equipmentDTO);

    void deleteEquipment(String id);
}
