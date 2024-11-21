package lk.ijse.cropmanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lk.ijse.cropmanagement.dto.impl.LogDTO;
import lk.ijse.cropmanagement.dao.LogsDAO;
import lk.ijse.cropmanagement.dao.StaffDAO;
import lk.ijse.cropmanagement.dao.FieldDAO;
import lk.ijse.cropmanagement.dao.CropDAO;
import lk.ijse.cropmanagement.utill.AppUtill;
import lk.ijse.cropmanagement.entity.impl.CropEntity;
import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import lk.ijse.cropmanagement.entity.impl.LogEntity;
import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lk.ijse.cropmanagement.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogsServiceImpl implements LogsService {
    @Autowired
    private LogsDAO logsDAO;

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private AppUtill appUtill;

   /* @Override
    public LogDTO saveLog(LogDTO logDTO) {
        // Create LogEntity and set basic fields
        LogEntity logEntity = new LogEntity();
        logEntity.setLogId(appUtill.generateId("LOG"));
        logEntity.setLogDetails(logDTO.getLogDetails());
        logEntity.setDate(new java.sql.Date(logDTO.getDate().getTime()));
        logEntity.setImage2(appUtill.imageToBase64(logDTO.getImage2().getBytes()));  // Assuming image2 is a byte array

        // Handle staff logs (sort by staffId)
        Set<StaffEntity> staffEntities = new HashSet<>();
        if (logDTO.getStaffIds() != null) {
            for (String staffId : logDTO.getStaffIds()) {
                StaffEntity staffEntity = staffDAO.findById(staffId).orElse(null);
                if (staffEntity != null) {
                    staffEntities.add(staffEntity);
                }
            }
        }
        staffEntities = staffEntities.stream()
                .sorted((staff1, staff2) -> staff1.getStaffId().compareTo(staff2.getStaffId()))  // Sort by staffId
                .collect(Collectors.toCollection(HashSet::new));
        logEntity.setStaffLogs(staffEntities);

        // Handle field logs (sort by fieldId)
        Set<FieldEntity> fieldEntities = new HashSet<>();
        if (logDTO.getFieldIds() != null) {
            for (String fieldId : logDTO.getFieldIds()) {
                FieldEntity fieldEntity = fieldDAO.findById(fieldId).orElse(null);
                if (fieldEntity != null) {
                    fieldEntities.add(fieldEntity);
                }
            }
        }
        fieldEntities = fieldEntities.stream()
                .sorted((field1, field2) -> field1.getFieldId().compareTo(field2.getFieldId()))  // Sort by fieldId
                .collect(Collectors.toCollection(HashSet::new));
        logEntity.setFieldLogs(fieldEntities);

        // Handle crop logs (sort by cropId)
        Set<CropEntity> cropEntities = new HashSet<>();
        if (logDTO.getCropIds() != null) {
            for (String cropId : logDTO.getCropIds()) {
                CropEntity cropEntity = cropDAO.findById(cropId).orElse(null);
                if (cropEntity != null) {
                    cropEntities.add(cropEntity);
                }
            }
        }
        cropEntities = cropEntities.stream()
                .sorted((crop1, crop2) -> crop1.getCropId().compareTo(crop2.getCropId()))  // Sort by cropId
                .collect(Collectors.toCollection(HashSet::new));
        logEntity.setCropLogs(cropEntities);

        // Save the log entity and its associations
        logsDAO.save(logEntity);

        // Convert the saved LogEntity back to DTO and return it
        return new LogDTO(
                logEntity.getLogId(),
                logEntity.getLogDetails(),
                logEntity.getDate(),
                logEntity.getImage2(),
                logDTO.getStaffIds(),
                logDTO.getFieldIds(),
                logDTO.getCropIds()
        );
    }*/
   @Override
   public LogDTO saveLog(LogDTO logDTO) {
       // Create LogEntity and set basic fields
       LogEntity logEntity = new LogEntity();
       logEntity.setLogId(appUtill.generateId("LOG"));
       logEntity.setLogDetails(logDTO.getLogDetails());
       logEntity.setDate(new java.sql.Date(logDTO.getDate().getTime()));
       logEntity.setImage2(appUtill.imageToBase64(logDTO.getImage2().getBytes()));  // Assuming image2 is a byte array

       // Handle staff logs (sort by staffId)
       Set<StaffEntity> staffEntities = new HashSet<>();
       if (logDTO.getStaffIds() != null) {
           for (String staffId : logDTO.getStaffIds()) {
               StaffEntity staffEntity = staffDAO.findById(staffId).orElse(null);
               if (staffEntity != null) {
                   staffEntities.add(staffEntity);
               }
           }

           // Sort staffEntities by staffId using a list and re-add sorted items into the set
           List<StaffEntity> sortedStaffEntities = new ArrayList<>(staffEntities);
           sortedStaffEntities.sort((staff1, staff2) -> staff1.getStaffId().compareTo(staff2.getStaffId()));
           staffEntities.clear();
           staffEntities.addAll(sortedStaffEntities);
       }
       logEntity.setStaffLogs(staffEntities);

       // Handle field logs (sort by fieldId)
       Set<FieldEntity> fieldEntities = new HashSet<>();
       if (logDTO.getFieldIds() != null) {
           for (String fieldId : logDTO.getFieldIds()) {
               FieldEntity fieldEntity = fieldDAO.findById(fieldId).orElse(null);
               if (fieldEntity != null) {
                   fieldEntities.add(fieldEntity);
               }
           }

           // Sort fieldEntities by fieldId using a list and re-add sorted items into the set
           List<FieldEntity> sortedFieldEntities = new ArrayList<>(fieldEntities);
           sortedFieldEntities.sort((field1, field2) -> field1.getFieldId().compareTo(field2.getFieldId()));
           fieldEntities.clear();
           fieldEntities.addAll(sortedFieldEntities);
       }
       logEntity.setFieldLogs(fieldEntities);

       // Handle crop logs (sort by cropId)
       Set<CropEntity> cropEntities = new HashSet<>();
       if (logDTO.getCropIds() != null) {
           for (String cropId : logDTO.getCropIds()) {
               CropEntity cropEntity = cropDAO.findById(cropId).orElse(null);
               if (cropEntity != null) {
                   cropEntities.add(cropEntity);
               }
           }

           // Sort cropEntities by cropId using a list and re-add sorted items into the set
           List<CropEntity> sortedCropEntities = new ArrayList<>(cropEntities);
           sortedCropEntities.sort((crop1, crop2) -> crop1.getCropId().compareTo(crop2.getCropId()));
           cropEntities.clear();
           cropEntities.addAll(sortedCropEntities);
       }
       logEntity.setCropLogs(cropEntities);

       // Save the log entity and its associations
       logsDAO.save(logEntity);

       // Convert the saved LogEntity back to DTO and return it
       return new LogDTO(
               logEntity.getLogId(),
               logEntity.getLogDetails(),
               logEntity.getDate(),
               logEntity.getImage2(),
               logDTO.getStaffIds(),
               logDTO.getFieldIds(),
               logDTO.getCropIds()
       );
   }
    @Override
    public void deleteLog(String logId) {
        if (!logsDAO.existsById(logId)) {
            throw new RuntimeException("Log not found for ID: " + logId);
        }
        logsDAO.deleteById(logId);
    }
    @Override
    public List<LogDTO> getAllLogs() {
        List<LogEntity> logEntities = logsDAO.findAll();

        return logEntities.stream().map(logEntity -> new LogDTO(
                logEntity.getLogId(),
                logEntity.getLogDetails(),
                logEntity.getDate(),
                logEntity.getImage2(),
                logEntity.getStaffLogs().stream().map(StaffEntity::getStaffId).collect(Collectors.toSet()), // Collect to Set
                logEntity.getFieldLogs().stream().map(FieldEntity::getFieldId).collect(Collectors.toSet()), // Collect to Set
                logEntity.getCropLogs().stream().map(CropEntity::getCropId).collect(Collectors.toSet()) // Collect to Set
        )).collect(Collectors.toList());
    }


    @Override
    public LogDTO getLogById(String logId) {
        LogEntity logEntity = logsDAO.findById(logId)
                .orElseThrow(() -> new RuntimeException("Log not found for ID: " + logId));

        return new LogDTO(
                logEntity.getLogId(),
                logEntity.getLogDetails(),
                logEntity.getDate(),
                logEntity.getImage2(),
                logEntity.getStaffLogs().stream().map(StaffEntity::getStaffId).collect(Collectors.toSet()), // Collect to Set
                logEntity.getFieldLogs().stream().map(FieldEntity::getFieldId).collect(Collectors.toSet()), // Collect to Set
                logEntity.getCropLogs().stream().map(CropEntity::getCropId).collect(Collectors.toSet()) // Collect to Set
        );
    }

    @Override
    public LogDTO updateLog(String logId, LogDTO logDTO) {
        // Fetch existing LogEntity
        LogEntity logEntity = logsDAO.findById(logId).orElseThrow(() -> new EntityNotFoundException("Log not found with id: " + logId));

        // Update basic fields
        logEntity.setLogDetails(logDTO.getLogDetails());
        logEntity.setDate(new java.sql.Date(logDTO.getDate().getTime()));

        // Update image if provided
        if (logDTO.getImage2() != null && !logDTO.getImage2().isEmpty()) {
            logEntity.setImage2(appUtill.imageToBase64(logDTO.getImage2().getBytes()));
        }

        // Update staff logs
        Set<StaffEntity> updatedStaffEntities = new HashSet<>();
        if (logDTO.getStaffIds() != null) {
            for (String staffId : logDTO.getStaffIds()) {
                StaffEntity staffEntity = staffDAO.findById(staffId).orElse(null);
                if (staffEntity != null) {
                    updatedStaffEntities.add(staffEntity);
                }
            }
            // Sort staffEntities by staffId
            List<StaffEntity> sortedStaffEntities = new ArrayList<>(updatedStaffEntities);
            sortedStaffEntities.sort((staff1, staff2) -> staff1.getStaffId().compareTo(staff2.getStaffId()));
            updatedStaffEntities.clear();
            updatedStaffEntities.addAll(sortedStaffEntities);
        }
        logEntity.setStaffLogs(updatedStaffEntities);

        // Update field logs
        Set<FieldEntity> updatedFieldEntities = new HashSet<>();
        if (logDTO.getFieldIds() != null) {
            for (String fieldId : logDTO.getFieldIds()) {
                FieldEntity fieldEntity = fieldDAO.findById(fieldId).orElse(null);
                if (fieldEntity != null) {
                    updatedFieldEntities.add(fieldEntity);
                }
            }
            // Sort fieldEntities by fieldId
            List<FieldEntity> sortedFieldEntities = new ArrayList<>(updatedFieldEntities);
            sortedFieldEntities.sort((field1, field2) -> field1.getFieldId().compareTo(field2.getFieldId()));
            updatedFieldEntities.clear();
            updatedFieldEntities.addAll(sortedFieldEntities);
        }
        logEntity.setFieldLogs(updatedFieldEntities);

        // Update crop logs
        Set<CropEntity> updatedCropEntities = new HashSet<>();
        if (logDTO.getCropIds() != null) {
            for (String cropId : logDTO.getCropIds()) {
                CropEntity cropEntity = cropDAO.findById(cropId).orElse(null);
                if (cropEntity != null) {
                    updatedCropEntities.add(cropEntity);
                }
            }
            // Sort cropEntities by cropId
            List<CropEntity> sortedCropEntities = new ArrayList<>(updatedCropEntities);
            sortedCropEntities.sort((crop1, crop2) -> crop1.getCropId().compareTo(crop2.getCropId()));
            updatedCropEntities.clear();
            updatedCropEntities.addAll(sortedCropEntities);
        }
        logEntity.setCropLogs(updatedCropEntities);

        // Save the updated log entity
        logsDAO.save(logEntity);

        // Convert the updated LogEntity back to DTO and return it
        return new LogDTO(
                logEntity.getLogId(),
                logEntity.getLogDetails(),
                logEntity.getDate(),
                logEntity.getImage2(),
                logDTO.getStaffIds(),
                logDTO.getFieldIds(),
                logDTO.getCropIds()
        );
    }


}
