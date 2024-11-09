package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CropDTO {
    private String cropId;
    private String commonName;
    private String specificName;
    private String category;
    private String season;
    private String image1;
    private String fieldId; // Representing FieldEntity by ID
}
