package lk.ijse.cropmanagement.customStatusCode;

import lk.ijse.cropmanagement.dto.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedCropErrorStatus implements CropStatus {
    private int statusCode;
    private String statusMessage;
}
