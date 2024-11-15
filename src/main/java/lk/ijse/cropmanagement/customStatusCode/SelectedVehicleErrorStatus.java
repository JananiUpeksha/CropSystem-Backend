package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.dto.VehicleStatus;
import lk.ijse.cropmanagement.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedVehicleErrorStatus implements VehicleStatus {
    private int statusCode;
    private String statusMessage;
}
