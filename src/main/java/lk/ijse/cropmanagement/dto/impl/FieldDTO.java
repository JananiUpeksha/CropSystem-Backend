/*
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
*/
package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.geo.Point;

import java.awt.*;
import java.util.Map;

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

    // Using Map<String, String> to store staff IDs and potentially other metadata
    private Map<String, String> staffIds; // Staff ID as key and staff name as value

    // Using Map<String, String> to store crop IDs and potentially other metadata
    private Map<String, String> cropIds; // Crop ID as key and crop name as value

    public void setLocation(org.springframework.data.geo.Point point) {
        this.location = point;  // Assign the input 'point' to the 'location' field
    }

}

