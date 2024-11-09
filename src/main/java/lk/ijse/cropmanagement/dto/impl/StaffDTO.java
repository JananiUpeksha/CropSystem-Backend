package lk.ijse.cropmanagement.dto.impl;

import lk.ijse.cropmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffDTO {
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String address;
    private String contact;
    private Date joinDate;
    private Role role;
    private String userId;                // Representing UserEntity by ID
    private List<FieldDTO> fieldIds;        // Representing FieldEntity list by IDs
    private List<VehicleDTO> vehicleIds;      // Representing VehicleEntity list by IDs
}