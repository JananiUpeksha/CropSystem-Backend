package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.LogDTO;
import lk.ijse.cropmanagement.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;

    // Create a new log
    @PostMapping
    public ResponseEntity<LogDTO> createLog(@RequestParam String logDetails,
                                            @RequestParam String date,
                                            @RequestParam(required = false) MultipartFile image2,
                                            @RequestParam(required = false) Set<String> staffIds,
                                            @RequestParam(required = false) Set<String> fieldIds,
                                            @RequestParam(required = false) Set<String> cropIds) {

        LogDTO logDTO = new LogDTO();
        logDTO.setLogDetails(logDetails);
        logDTO.setDate(java.sql.Date.valueOf(date));  // Assuming date is in "yyyy-MM-dd" format

        // Handle image2 if provided
        try {
            if (image2 != null) {
                logDTO.setImage2(Base64.getEncoder().encodeToString(image2.getBytes()));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Set the associated entities' IDs
        logDTO.setStaffIds(staffIds);
        logDTO.setFieldIds(fieldIds);
        logDTO.setCropIds(cropIds);

        // Save the log
        LogDTO createdLog = logsService.saveLog(logDTO);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    // Get all logs
    @GetMapping
    public ResponseEntity<List<LogDTO>> getAllLogs() {
        List<LogDTO> logs = logsService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    // Get a specific log by ID
    @GetMapping("/{id}")
    public ResponseEntity<LogDTO> getLogById(@PathVariable String id) {
        try {
            LogDTO log = logsService.getLogById(id);
            return ResponseEntity.ok(log);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a log
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable String id) {
        try {
            logsService.deleteLog(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{logId}")
    public ResponseEntity<LogDTO> updateLog(@PathVariable String logId,
                                            @RequestParam String logDetails,
                                            @RequestParam String date,
                                            @RequestParam(required = false) MultipartFile image2,
                                            @RequestParam(required = false) Set<String> staffIds,
                                            @RequestParam(required = false) Set<String> fieldIds,
                                            @RequestParam(required = false) Set<String> cropIds) {

        LogDTO logDTO = new LogDTO();
        logDTO.setLogDetails(logDetails);
        logDTO.setDate(java.sql.Date.valueOf(date)); // Assuming date is in "yyyy-MM-dd" format

        // Handle image2 if provided
        try {
            if (image2 != null) {
                logDTO.setImage2(Base64.getEncoder().encodeToString(image2.getBytes()));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Set associated entities' IDs
        logDTO.setStaffIds(staffIds);
        logDTO.setFieldIds(fieldIds);
        logDTO.setCropIds(cropIds);

        // Update the log
        LogDTO updatedLog = logsService.updateLog(logId, logDTO);
        return new ResponseEntity<>(updatedLog, HttpStatus.OK);
    }

}
