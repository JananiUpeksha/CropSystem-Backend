package lk.ijse.cropmanagement.dto.impl;

import lk.ijse.cropmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String userId;
    private String email;
    private String password;
    private Role role;
    private StaffDTO staff;
}
