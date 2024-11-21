package lk.ijse.cropmanagement.service;

import lk.ijse.cropmanagement.dto.impl.LogDTO;

import java.util.List;

public interface LogsService {
    LogDTO saveLog(LogDTO logDTO);

    List<LogDTO> getAllLogs();

    LogDTO getLogById(String id);

    void deleteLog(String id);

    LogDTO updateLog(String logId, LogDTO logDTO);
}
