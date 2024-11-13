package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {

    FieldDTO saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldDTO getFieldById(String id);

    FieldDTO updateField(FieldDTO dto);

    void deleteField(String id);
}
