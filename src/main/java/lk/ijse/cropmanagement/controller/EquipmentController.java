package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.EquipmentDTO;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentDTO> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            EquipmentDTO savedEquipment = equipmentService.saveEquipment(equipmentDTO);
            logger.info("Equipment saved successfully");
            return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment() {
        logger.info("Fetching all Equipments");
        List<EquipmentDTO> equipmentList = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable String id) {
        try {
            EquipmentDTO equipment = equipmentService.getEquipmentById(id);
            logger.info("Fetching all Equipments");
            return ResponseEntity.ok(equipment);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable String id, @RequestBody EquipmentDTO equipmentDTO) {
        try {
            EquipmentDTO updatedEquipment = equipmentService.updateEquipment(id, equipmentDTO);
            return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataPersistException e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
