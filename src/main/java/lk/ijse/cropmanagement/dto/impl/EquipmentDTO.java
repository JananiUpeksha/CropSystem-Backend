package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipmentDTO {
    private String equipmentId;
    private String type;
    private String name;
    private String status;
    private String fieldId;  // Representing FieldEntity by ID
    private String staffId;  // Representing StaffEntity by ID
}

