package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.FieldDTO;
import lk.ijse.cropmanagement.service.FieldService;
import lk.ijse.cropmanagement.utill.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;
    @PostMapping
    public ResponseEntity<FieldDTO> saveField(
            @RequestParam String name,
            @RequestParam Double size,
            @RequestParam Point location,
            @RequestParam List<String> staffIds,
            @RequestParam MultipartFile image1,
            @RequestParam MultipartFile image2) {

        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setName(name);
        fieldDTO.setSize(size);
        fieldDTO.setLocation(location);

        // Convert List to Set
        Set<String> staffIdSet = new HashSet<>(staffIds);
        fieldDTO.setStaffIds(staffIdSet);

        try {
            fieldDTO.setImage1(Base64.getEncoder().encodeToString(image1.getBytes()));
            fieldDTO.setImage2(Base64.getEncoder().encodeToString(image2.getBytes()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        FieldDTO savedField = fieldService.saveField(fieldDTO);
        return ResponseEntity.ok(savedField);
    }

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        List<FieldDTO> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable String id) {
        FieldDTO field = fieldService.getFieldById(id);
        return ResponseEntity.ok(field);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FieldDTO> updateField(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam Double size,
            @RequestParam Point location,
            @RequestParam List<String> staffIds,
            @RequestParam(required = false) MultipartFile image1,
            @RequestParam(required = false) MultipartFile image2) {

        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldId(id);  // Assuming the ID is used to locate the entity
        fieldDTO.setName(name);
        fieldDTO.setSize(size);
        fieldDTO.setLocation(location);
        fieldDTO.setStaffIds(Set.copyOf(staffIds));

        try {
            if (image1 != null) {
                fieldDTO.setImage1(Arrays.toString(image1.getBytes()));
            }
            if (image2 != null) {
                fieldDTO.setImage2(Arrays.toString(image2.getBytes()));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        FieldDTO updatedField = fieldService.updateField(fieldDTO);
        return ResponseEntity.ok(updatedField);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable String id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }

}