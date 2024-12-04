package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.FieldDTO;
import lk.ijse.cropmanagement.service.FieldService;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.utill.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", allowCredentials = "true", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;
    @CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", allowCredentials = "true")
    //@PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<FieldDTO> saveField(
            @RequestParam String name,
            @RequestParam Double size,
            @RequestParam String location,
            @RequestParam List<String> staffIds,
            @RequestParam(required = false) MultipartFile image1,
            @RequestParam(required = false) MultipartFile image2,
            Principal principal) {  // Added Principal here

        // Print the name of the currently authenticated user
        System.out.println("Principal: " + principal.getName());

        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setName(name);
        fieldDTO.setSize(size);
        fieldDTO.setLocation(location);

        // Convert List to Set
        Set<String> staffIdSet = new HashSet<>(staffIds);
        fieldDTO.setStaffIds(staffIdSet);

        try {
            if (image1 != null) {
                fieldDTO.setImage1(AppUtill.imageToBase64(image1.getBytes()));
            }
            if (image2 != null) {
                fieldDTO.setImage2(AppUtill.imageToBase64(image2.getBytes()));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        FieldDTO savedField = fieldService.saveField(fieldDTO);
        System.out.println("Received name: " + name);
        System.out.println("Received size: " + size);
        System.out.println("Received location: " + location);
        System.out.println("Received staffIds: " + staffIds);
        System.out.println("Image1: " + (image1 != null ? image1.getOriginalFilename() : "null"));
        System.out.println("Image2: " + (image2 != null ? image2.getOriginalFilename() : "null"));
        return ResponseEntity.ok(savedField);
    }

    @CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        List<FieldDTO> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }
    @CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable String id) {
        FieldDTO field = fieldService.getFieldById(id);
        return ResponseEntity.ok(field);
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping("/{id}")
    public ResponseEntity<FieldDTO> updateField(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam Double size,
            @RequestParam String location,
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
    @CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable String id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }

}