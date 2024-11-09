package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import lk.ijse.cropmanagement.exception.DataPersistException;
import lk.ijse.cropmanagement.service.StaffService;
import lk.ijse.cropmanagement.utill.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO) {
        try {
            // Log incoming request
            System.out.println("Received StaffDTO: " + staffDTO);

            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            // Log exception to understand the specific error
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            // Log general exception
            System.out.println("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
