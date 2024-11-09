package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.dto.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedEquipmentErrorStatus implements EquipmentStatus {
    private int statusCode;
    private String statusMessage;
}
