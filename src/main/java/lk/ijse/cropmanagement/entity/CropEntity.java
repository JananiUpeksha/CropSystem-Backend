package lk.ijse.cropmanagement.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "crop")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropEntity {
    @Id
    private String cropId;

    private String commonName;
    private String specificName;
    private String category;
    private String season;
    @Column(columnDefinition = "LONGTEXT")
    private String image1;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;
}
