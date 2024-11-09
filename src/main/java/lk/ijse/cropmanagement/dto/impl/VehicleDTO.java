package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VehicleDTO {
    private String vehicleId;
    private String plateNumber;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
    private String staffId;
}
