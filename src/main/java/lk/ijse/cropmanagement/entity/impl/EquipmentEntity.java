package lk.ijse.cropmanagement.entity.impl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String type;
    private String name;
    private String status;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;
}
