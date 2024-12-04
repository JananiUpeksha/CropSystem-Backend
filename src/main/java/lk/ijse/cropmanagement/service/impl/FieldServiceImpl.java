package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.FieldDAO;
import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dto.impl.FieldDTO;
import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.service.FieldService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private Mapping mapping;

    @Autowired
    private AppUtill appUtill;


    @Override
    public FieldDTO saveField(FieldDTO fieldDTO) {
        // Generate and set field ID
        fieldDTO.setFieldId(appUtill.generateId("FIELD"));

        // Convert DTO to Entity
        FieldEntity field = mapping.toFieldEntity(fieldDTO);

        try {
            Set<StaffEntity> staffEntities = new HashSet<>();

            // Fetch associated Staff entities
            if (fieldDTO.getStaffIds() != null) {
                for (String staffId : fieldDTO.getStaffIds()) {
                    StaffEntity staff = staffDAO.findById(staffId)
                            .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + staffId));
                    staffEntities.add(staff);
                }
            }
            field.setStaffMembers(staffEntities);

            // Save Field entity
            FieldEntity savedField = fieldDAO.save(field);

            // Convert back to DTO and return
            return mapping.toFieldDTO(savedField);

        } catch (Exception e) {
            throw new RuntimeException("Failed to save Field entity: " + e.getMessage(), e);
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        List<FieldEntity> fields = fieldDAO.findAll();
        return fields.stream()
                .map(mapping::toFieldDTO)
                .collect(Collectors.toList());
    }

    /*@Override
    public FieldDTO getFieldById(String id) {
        FieldEntity field = fieldDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + id));
        return mapping.toFieldDTO(field);
    }*/
    @Override
    public FieldDTO getFieldById(String id) {
        FieldEntity field = fieldDAO.findByFieldIdWithStaff(id)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + id));

        // Map the FieldEntity to FieldDTO
        FieldDTO fieldDTO = mapping.toFieldDTO(field);

        // Extract staff IDs
        Set<String> staffIds = field.getStaffMembers().stream()
                .map(StaffEntity::getStaffId)
                .collect(Collectors.toSet());
        fieldDTO.setStaffIds(staffIds);

        return fieldDTO;
    }


    @Override
    public FieldDTO updateField(FieldDTO dto) {
        FieldEntity field = fieldDAO.findById(dto.getFieldId())
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + dto.getFieldId()));

        // Update fields
        field.setName(dto.getName());
        field.setSize(dto.getSize());
        field.setLocation(dto.getLocation());

        if (dto.getImage1() != null) field.setImage1(dto.getImage1());
        if (dto.getImage2() != null) field.setImage2(dto.getImage2());

        Set<StaffEntity> updatedStaffEntities = dto.getStaffIds().stream()
                .map(staffId -> staffDAO.findById(staffId)
                        .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + staffId)))
                .collect(Collectors.toSet());
        field.setStaffMembers(updatedStaffEntities);

        FieldEntity updatedField = fieldDAO.save(field);
        return mapping.toFieldDTO(updatedField);
    }

    @Override
    public void deleteField(String id) {
        FieldEntity field = fieldDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + id));
        fieldDAO.delete(field);
    }
}

