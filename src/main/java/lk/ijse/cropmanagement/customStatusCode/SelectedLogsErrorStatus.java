package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.dto.LogsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedLogsErrorStatus implements LogsStatus {
    private int statusCode;
    private String statusMessage;
}
