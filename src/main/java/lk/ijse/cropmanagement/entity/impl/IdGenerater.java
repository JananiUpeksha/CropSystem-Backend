package lk.ijse.cropmanagement.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "id_generator")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdGenerater {
        @Id
        private String entityName;
        private String lastId;  // It will hold the ID as a string (but incremented numerically)
}
