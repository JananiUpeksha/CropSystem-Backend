package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.EquipmentDAO;
import lk.ijse.cropmanagement.dao.FieldDAO;
import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dto.impl.EquipmentDTO;
import lk.ijse.cropmanagement.entity.impl.EquipmentEntity;
import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.EquipmentService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private AppUtill appUtill;
    @Autowired
    private Mapping mapping;

    @Override
    public EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(appUtill.generateId("EQUIPMENT"));
        EquipmentEntity equipment = mapping.toEquipmentEntity(equipmentDTO);

        if (equipmentDTO.getFieldId() != null) {
            FieldEntity field = fieldDAO.findById(equipmentDTO.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + equipmentDTO.getFieldId()));
            equipment.setField(field);
        }
        if (equipmentDTO.getStaffId() != null) {
            StaffEntity staff = staffDAO.findById(equipmentDTO.getStaffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + equipmentDTO.getStaffId()));
            equipment.setStaff(staff);
        }

        EquipmentEntity savedEquipment = equipmentDAO.save(equipment);
        if (savedEquipment == null) {
            throw new DataPersistException("Equipment not saved");
        }

        return mapping.toEquipmentDTO(savedEquipment);
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentDAO.findAll().stream()
                .map(mapping::toEquipmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EquipmentDTO getEquipmentById(String equipmentId) {
        EquipmentEntity equipment = equipmentDAO.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found with ID: " + equipmentId));
        return mapping.toEquipmentDTO(equipment);
    }

    @Override
    public EquipmentDTO updateEquipment(String id, EquipmentDTO equipmentDTO) {
        EquipmentEntity equipment = equipmentDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found with ID: " + id));

        equipment.setName(equipmentDTO.getName());
        equipment.setType(equipmentDTO.getType());
        equipment.setStatus(equipmentDTO.getStatus());

        if (equipmentDTO.getFieldId() != null) {
            FieldEntity field = fieldDAO.findById(equipmentDTO.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + equipmentDTO.getFieldId()));
            equipment.setField(field);
        }
        if (equipmentDTO.getStaffId() != null) {
            StaffEntity staff = staffDAO.findById(equipmentDTO.getStaffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + equipmentDTO.getStaffId()));
            equipment.setStaff(staff);
        }

        EquipmentEntity updatedEquipment = equipmentDAO.save(equipment);
        return mapping.toEquipmentDTO(updatedEquipment);
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        EquipmentEntity equipment = equipmentDAO.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found with ID: " + equipmentId));
        equipmentDAO.delete(equipment);
    }
}
