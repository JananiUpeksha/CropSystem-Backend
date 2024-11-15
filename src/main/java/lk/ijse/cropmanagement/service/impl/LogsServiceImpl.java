package lk.ijse.cropmanagement.service.impl;

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
    }
}
