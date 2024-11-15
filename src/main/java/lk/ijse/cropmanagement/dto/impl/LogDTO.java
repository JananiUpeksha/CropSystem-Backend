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
    private Set<String> staffIds;   // IDs of staff members monitoring this log
    private Set<String> fieldIds;     // IDs of fields related to this log
    private Set<String> cropIds;      // IDs of crops associated with this log
}
