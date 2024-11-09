package lk.ijse.cropmanagement.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogDTO {
    private String logId;
    private String logDetails;
    private Date date;
    private String image2;
    private List<StaffDTO> staffLogIds; // Representing StaffEntity by IDs
    private List<FieldDTO> fieldLogIds; // Representing FieldEntity by IDs
    private List<FieldDTO> cropLogIds;  // Representing CropEntity by IDs
}
