package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedStaffErrorStatus implements StaffStatus {
    private int statusCode;
    private String statusMessage;
}
