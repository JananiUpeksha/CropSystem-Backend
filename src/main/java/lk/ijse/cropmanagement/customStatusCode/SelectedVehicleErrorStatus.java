package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedVehicleErrorStatus implements VehicleService {
    private int statusCode;
    private String statusMessage;
}
