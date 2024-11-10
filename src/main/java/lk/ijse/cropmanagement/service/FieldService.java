package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    FieldDTO getFieldById(String fieldId);

    List<FieldDTO> getAllFields();

    void deleteField(String fieldId);

    void updateField(String fieldId, FieldDTO fieldDTO);
}
