package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.FieldDAO;
import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dto.impl.FieldDTO;
import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.FieldService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    // Synchronize ID generation to avoid race conditions
    private final Object lock = new Object();  // Lock object for synchronization

    @Override
    public void saveField(FieldDTO fieldDTO) {
        // Generate the Field ID dynamically
        synchronized (lock) {
            String generatedFieldId = appUtill.generateId("FIELD");
            fieldDTO.setFieldId(generatedFieldId);
        }

        // Encode image data if provided
        encodeImage(fieldDTO);

        // Map FieldDTO to FieldEntity
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDTO);

        // Manually assign the generated field ID to the entity
        // This ensures the ID is set before the entity is persisted
        if (fieldEntity.getFieldId() == null) {
            fieldEntity.setFieldId(appUtill.generateId("FIELD"));
        }

        // Fetch StaffEntity objects using staffIds (keys)
        Set<StaffEntity> staffEntities = fieldDTO.getStaffIds().keySet().stream()
                .map(staffId -> staffDAO.findById(staffId)
                        .orElseThrow(() -> new RuntimeException("Staff not found for ID: " + staffId)))
                .collect(Collectors.toSet());

        // Set the staff members for the field
        fieldEntity.setStaffMembers(staffEntities);

        // Save the FieldEntity to the database
        FieldEntity savedField = fieldDAO.save(fieldEntity);

        // If the field wasn't saved, throw an exception
        if (savedField == null) {
            throw new DataPersistException("Field not saved");
        }

        // Update the staff entities to reflect the new field association
        for (StaffEntity staffEntity : staffEntities) {
            staffEntity.getFields().add(savedField);  // Add the field to staff's set of fields
        }
    }


    private void encodeImage(FieldDTO fieldDTO) {
        if (fieldDTO.getImage1() != null) {
            fieldDTO.setImage1(appUtill.profilePicToBase64(fieldDTO.getImage1().getBytes()));
        }
        if (fieldDTO.getImage2() != null) {
            fieldDTO.setImage2(appUtill.profilePicToBase64(fieldDTO.getImage2().getBytes()));
        }
    }

    @Override
    public FieldDTO getFieldById(String fieldId) {
        Optional<FieldEntity> fieldEntity = fieldDAO.findById(fieldId);
        if (!fieldEntity.isPresent()) {
            throw new RuntimeException("Field not found for ID: " + fieldId);
        }
        return mapping.toFieldDTO(fieldEntity.get());
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return fieldDAO.findAll().stream()
                .map(mapping::toFieldDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteField(String fieldId) {
        Optional<FieldEntity> fieldEntity = fieldDAO.findById(fieldId);
        if (!fieldEntity.isPresent()) {
            throw new RuntimeException("Field not found for ID: " + fieldId);
        }
        fieldDAO.deleteById(fieldId);
    }

    @Override
    public void updateField(String fieldId, FieldDTO fieldDTO) {
        Optional<FieldEntity> fieldEntity = fieldDAO.findById(fieldId);
        if (!fieldEntity.isPresent()) {
            throw new RuntimeException("Field not found for ID: " + fieldId);
        }
        FieldEntity existingField = fieldEntity.get();

        existingField.setName(fieldDTO.getName());
        java.awt.Point location = fieldDTO.getLocation();
        if (location != null) {
            existingField.setLocation(new Point(location.getX(), location.getY()));
        }
        existingField.setSize(fieldDTO.getSize());

        if (fieldDTO.getImage1() != null) {
            existingField.setImage1(appUtill.profilePicToBase64(fieldDTO.getImage1().getBytes()));
        }
        if (fieldDTO.getImage2() != null) {
            existingField.setImage2(appUtill.profilePicToBase64(fieldDTO.getImage2().getBytes()));
        }

        fieldDAO.save(existingField);
    }
}
