package lk.ijse.cropmanagement.utill;

import java.util.regex.Pattern;

public class RegexProcess {
    // Validate Field ID (e.g., FIELD001, FIELD002, FIELD003, etc.)
    public static boolean fieldIdMatcher(String fieldId) {
        String regexForFieldId = "^FIELD\\d{3}$"; // Validates FIELD001, FIELD002, etc.
        Pattern regexPattern = Pattern.compile(regexForFieldId);
        return regexPattern.matcher(fieldId).matches();
    }

    // Validate Staff ID (e.g., STAFF001, STAFF002, STAFF003, etc.)
    public static boolean staffIdMatcher(String staffId) {
        String regexForStaffId = "^STAFF\\d{3}$"; // Validates STAFF001, STAFF002, etc.
        Pattern regexPattern = Pattern.compile(regexForStaffId);
        return regexPattern.matcher(staffId).matches();
    }

    // Validate Equipment ID (e.g., EQUIP001, EQUIP002, EQUIP003, etc.)
    public static boolean equipmentIdMatcher(String equipmentId) {
        String regexForEquipmentId = "^EQUIP\\d{3}$"; // Validates EQUIP001, EQUIP002, etc.
        Pattern regexPattern = Pattern.compile(regexForEquipmentId);
        return regexPattern.matcher(equipmentId).matches();
    }

    // Validate Crop ID (e.g., CROP001, CROP002, CROP003, etc.)
    public static boolean cropIdMatcher(String cropId) {
        String regexForCropId = "^CROP\\d{3}$"; // Validates CROP001, CROP002, etc.
        Pattern regexPattern = Pattern.compile(regexForCropId);
        return regexPattern.matcher(cropId).matches();
    }

    // Validate Vehicle ID (e.g., VEHICLE001, VEHICLE002, VEHICLE003, etc.)
    public static boolean vehicleIdMatcher(String vehicleId) {
        String regexForVehicleId = "^VEHICLE\\d{3}$"; // Validates VEHICLE001, VEHICLE002, etc.
        Pattern regexPattern = Pattern.compile(regexForVehicleId);
        return regexPattern.matcher(vehicleId).matches();
    }

    // Validate Log ID (e.g., LOG001, LOG002, LOG003, etc.)
    public static boolean logIdMatcher(String logId) {
        String regexForLogId = "^LOG\\d{3}$"; // Validates LOG001, LOG002, etc.
        Pattern regexPattern = Pattern.compile(regexForLogId);
        return regexPattern.matcher(logId).matches();
    }
}
