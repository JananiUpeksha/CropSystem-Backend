package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.FieldDTO;
import lk.ijse.cropmanagement.dto.impl.VehicleDTO;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        try{
            vehicleService.saveVehicle(vehicleDTO);
            logger.info("Vehicle saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            logger.error("Error occurred while saving vehicle: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        logger.info("Fetching all vehicles");
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        logger.info("Fetched {} vehicles", vehicles.size());
        return ResponseEntity.ok(vehicles);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable String id) {
        logger.info("Fetching all vehicles");
        VehicleDTO vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        logger.info("Fetching vehicle with ID: {}", id);
        vehicleService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable String id, @RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.updateVehicle(id, vehicleDTO);
            logger.info("Vehicle updated successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistException e) {
            e.printStackTrace();
            logger.error("Error occurred while updating vehicle with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred while updating vehicle with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
