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
*//*

package lk.ijse.cropmanagement.dto.impl;

import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.geo.Point;

import java.util.*;

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

    private Map<String, String> staffIds;

    // Using Map<String, String> to store crop IDs and potentially other metadata
    private Map<String, String> cropIds; // Crop ID as key and crop name as value

    public void setLocation(org.springframework.data.geo.Point point) {
        this.location = point;  // Assign the input 'point' to the 'location' field
    }

}

*/
package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.geo.Point;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FieldDTO {
    private String fieldId;
    private String name;
    private String location;
    private Double size;
    private String image1;
    private String image2;

    private Set<String> staffIds;  // Changed to Map for associating Staff IDs
    private Map<String, String> cropIds;   // Map for Crop IDs

}
