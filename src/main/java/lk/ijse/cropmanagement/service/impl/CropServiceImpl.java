package lk.ijse.cropmanagement.service.impl;

import lk.ijse.cropmanagement.dao.CropDAO;
import lk.ijse.cropmanagement.dao.FieldDAO;
import lk.ijse.cropmanagement.dto.impl.CropDTO;
import lk.ijse.cropmanagement.entity.impl.CropEntity;
import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import lk.ijse.cropmanagement.service.CropService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private Mapping mapping;
    @Autowired
    private AppUtill appUtill;

    @Override
    public CropDTO saveCrop(CropDTO cropDTO) {
        // Generate and set crop ID
        cropDTO.setCropId(appUtill.generateId("CROP"));

        // Convert DTO to Entity
        CropEntity cropEntity = mapping.toCropEntity(cropDTO);

        try {
            // Fetch associated Field entity if provided
            if (cropDTO.getFieldId() != null) {
                FieldEntity field = fieldDAO.findById(cropDTO.getFieldId())
                        .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + cropDTO.getFieldId()));
                cropEntity.setField(field);
            }

            // Save Crop entity
            CropEntity savedCropEntity = cropDAO.save(cropEntity);

            // Convert back to DTO and return
            return mapping.toCropDTO(savedCropEntity);

        } catch (Exception e) {
            throw new RuntimeException("Failed to save Crop entity: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> cropEntities = cropDAO.findAll();
        return cropEntities.stream()
                .map(mapping::toCropDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CropDTO getCropById(String id) {
        CropEntity cropEntity = cropDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with ID: " + id));
        return mapping.toCropDTO(cropEntity);
    }

    @Override
    public CropDTO updateCrop(CropDTO cropDTO) {
        CropEntity cropEntity = cropDAO.findById(cropDTO.getCropId())
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with ID: " + cropDTO.getCropId()));

        // Update fields
        cropEntity.setCommonName(cropDTO.getCommonName());
        cropEntity.setSpecificName(cropDTO.getSpecificName());
        cropEntity.setCategory(cropDTO.getCategory());
        cropEntity.setSeason(cropDTO.getSeason());
        cropEntity.setImage1(cropDTO.getImage1());

        // Fetch associated Field entity if provided
        if (cropDTO.getFieldId() != null) {
            FieldEntity field = fieldDAO.findById(cropDTO.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + cropDTO.getFieldId()));
            cropEntity.setField(field);
        }

        // Save Crop entity
        CropEntity updatedCropEntity = cropDAO.save(cropEntity);
        return mapping.toCropDTO(updatedCropEntity);
    }

    @Override
    public void deleteCrop(String id) {
        CropEntity cropEntity = cropDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with ID: " + id));
        cropDAO.delete(cropEntity);
    }
}
