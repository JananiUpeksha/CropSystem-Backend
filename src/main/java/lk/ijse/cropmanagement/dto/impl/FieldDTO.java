package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FieldDTO {
    private String fieldId;
    private String name;
    private Point location;
    private Double size;
    private String image1;
    private String image2;
    private List<StaffDTO> staffMemberIds; // Representing StaffEntity by IDs
    private List<CropDTO> cropIds;       // Representing CropEntity by IDs
}
