package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedFieldErrorStatus implements FieldStatus {
    private int statusCode;
    private String statusMessage;
}
