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
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping
    public ResponseEntity<?> saveField(@RequestParam("name") String name,
                                       @RequestParam("size") double size,
                                       @RequestParam("location") String location,
                                       @RequestParam(value = "image1", required = false) MultipartFile image1,
                                       @RequestParam(value = "image2", required = false) MultipartFile image2) {
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setName(name);
        fieldDTO.setSize(size);

        // Convert location string (e.g., "x,y") to Point
        if (location != null && !location.isEmpty()) {
            String[] coordinates = location.split(",");
            if (coordinates.length == 2) {
                try {
                    double x = Double.parseDouble(coordinates[0]);
                    double y = Double.parseDouble(coordinates[1]);
                    fieldDTO.setLocation(new Point(x, y));
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid coordinates format for location.");
                }
            } else {
                return ResponseEntity.badRequest().body("Location should be in the format 'x,y'.");
            }
        }

        // Handle image files if provided
        if (image1 != null) {
            try {
                fieldDTO.setImage1(imageToBase64(image1.getBytes()));
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Error processing image1.");
            }
        }
        if (image2 != null) {
            try {
                fieldDTO.setImage2(imageToBase64(image2.getBytes()));
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Error processing image2.");
            }
        }

        // Call service to save the field
        fieldService.saveField(fieldDTO);

        return ResponseEntity.ok("Field saved successfully");
    }

    // Helper method to convert image bytes to Base64 string
    private String imageToBase64(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }


    @GetMapping(value = "/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable("fieldId") String fieldId) {
        if (!RegexProcess.fieldIdMatcher(fieldId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            FieldDTO fieldDTO = fieldService.getFieldById(fieldId);
            return new ResponseEntity<>(fieldDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        return new ResponseEntity<>(fieldService.getAllFields(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldId") String fieldId) {
        try {
            if (!RegexProcess.fieldIdMatcher(fieldId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deleteField(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{fieldId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateField(@PathVariable("fieldId") String fieldId, @RequestBody FieldDTO fieldDTO) {
        try {
            if (!RegexProcess.fieldIdMatcher(fieldId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.updateField(fieldId, fieldDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


