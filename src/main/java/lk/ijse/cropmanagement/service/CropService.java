package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    CropDTO saveCrop(CropDTO cropDTO);

    List<CropDTO> getAllCrops();

    CropDTO getCropById(String id);

    CropDTO updateCrop(CropDTO cropDTO);

    void deleteCrop(String id);
}
