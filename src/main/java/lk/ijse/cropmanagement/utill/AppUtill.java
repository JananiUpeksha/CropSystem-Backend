package lk.ijse.cropmanagement.utill;

import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

public class AppUtill {
    private static final AtomicInteger fieldCounter = new AtomicInteger(0);
    private static final AtomicInteger staffCounter = new AtomicInteger(0);
    private static final AtomicInteger equipmentCounter = new AtomicInteger(0);
    private static final AtomicInteger cropCounter = new AtomicInteger(0);
    private static final AtomicInteger vehicleCounter = new AtomicInteger(0);
    private static final AtomicInteger logCounter = new AtomicInteger(0);

    // Method to generate Field IDs
    public static String generateFieldId() {
        return "FIELD" + String.format("%03d", fieldCounter.incrementAndGet());
    }

    // Method to generate Staff IDs
    public static String generateStaffId() {
        return "STAFF" + String.format("%03d", staffCounter.incrementAndGet());
    }

    // Method to generate Equipment IDs
    public static String generateEquipmentId() {
        return "EQUIP" + String.format("%03d", equipmentCounter.incrementAndGet());
    }

    // Method to generate Crop IDs
    public static String generateCropId() {
        return "CROP" + String.format("%03d", cropCounter.incrementAndGet());
    }

    // Method to generate Vehicle IDs
    public static String generateVehicleId() {
        return "VEHICLE" + String.format("%03d", vehicleCounter.incrementAndGet());
    }

    // Method to generate Log IDs
    public static String generateLogId() {
        return "LOG" + String.format("%03d", logCounter.incrementAndGet());
    }

    public static String profilePicToBase64(byte[] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
