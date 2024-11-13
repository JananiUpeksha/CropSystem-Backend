package lk.ijse.cropmanagement.entity.impl;
import jakarta.persistence.*;
import lk.ijse.cropmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private LocalDate dob;
    private String address;
    private String contact;
    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "staff")
    private UserEntity user;

    /*@ManyToMany
    @JoinTable(
            name = "staff_fields_detail",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    *//*private List<FieldEntity> fields;*//*
    private Set<FieldEntity> fields = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private List<VehicleEntity> vehicles;*/
    @ManyToMany(mappedBy = "staffMembers",cascade = CascadeType.ALL)
    private List<FieldEntity> fields;
    @ManyToMany(mappedBy = "staffLogs",cascade = CascadeType.ALL)
    private List<LogEntity> logs;

    @OneToMany(mappedBy = "staff",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<VehicleEntity> vehicles;


}
