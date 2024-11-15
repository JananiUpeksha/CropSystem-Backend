package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.CropDTO;
import lk.ijse.cropmanagement.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crops")
public class CropsController {
    @Autowired
    private CropService cropService;

        // Create a new crop
        @PostMapping
        public ResponseEntity<CropDTO> createCrop(@RequestParam String commonName,
                                                  @RequestParam String specificName,
                                                  @RequestParam String category,
                                                  @RequestParam String season,
                                                  @RequestParam(required = false) MultipartFile image1,
                                                  @RequestParam(required = false) String fieldId) {  // Added fieldId parameter

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCommonName(commonName);
            cropDTO.setSpecificName(specificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);

            // Handle image1 if provided
            try {
                if (image1 != null) {
                    cropDTO.setImage1(Base64.getEncoder().encodeToString(image1.getBytes()));
                }
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            // Set the Field ID if provided
            cropDTO.setFieldId(fieldId);  // Set the fieldId from the request

            // Save the crop
            CropDTO createdCrop = cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(createdCrop, HttpStatus.CREATED);
        }

        // Update an existing crop
        @PutMapping("/{id}")
        public ResponseEntity<CropDTO> updateCrop(@PathVariable String id,
                                                  @RequestParam String commonName,
                                                  @RequestParam String specificName,
                                                  @RequestParam String category,
                                                  @RequestParam String season,
                                                  @RequestParam(required = false) MultipartFile image1,
                                                  @RequestParam(required = false) String fieldId) {  // Added fieldId parameter

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropId(id);  // Set the ID for updating the crop
            cropDTO.setCommonName(commonName);
            cropDTO.setSpecificName(specificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);

            // Handle image1 if provided
            try {
                if (image1 != null) {
                    cropDTO.setImage1(Base64.getEncoder().encodeToString(image1.getBytes()));
                }
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            // Set the Field ID if provided
            cropDTO.setFieldId(fieldId);  // Set the fieldId from the request

            // Update the crop
            CropDTO updatedCrop = cropService.updateCrop(cropDTO);
            return new ResponseEntity<>(updatedCrop, HttpStatus.OK);
        }

        // Get all crops
        @GetMapping
        public ResponseEntity<List<CropDTO>> getAllCrops() {
            List<CropDTO> crops = cropService.getAllCrops();
            return new ResponseEntity<>(crops, HttpStatus.OK);
        }

        // Get a specific crop by ID
        @GetMapping("/{id}")
        public ResponseEntity<CropDTO> getCropById(@PathVariable String id) {
            CropDTO crop = cropService.getCropById(id);
            return new ResponseEntity<>(crop, HttpStatus.OK);
        }

        // Delete a crop
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCrop(@PathVariable String id) {
            cropService.deleteCrop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

