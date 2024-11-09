package lk.ijse.cropmanagement.entity.impl;
import jakarta.persistence.*;
import lk.ijse.cropmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "staff")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String address;
    private String contact;
    private Date joinDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "staff")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "staff_fields_detail",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff")
    private List<VehicleEntity> vehicles;
}
