package lk.ijse.cropmanagement.utill;

import lk.ijse.cropmanagement.dao.IdGeneraterDAO;
import lk.ijse.cropmanagement.entity.impl.IdGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUtill {

    @Autowired
    private IdGeneraterDAO idGeneraterDAO;  // Use the correct DAO

    // Method to generate IDs for any entity (STAFF, FIELD, etc.)
    public String generateId(String entityType) {
        // Fetch the entity from the database using the entity name (e.g., "STAFF")
        IdGenerater entity = idGeneraterDAO.findByEntityName(entityType);

        // If entity is not found, initialize it
        if (entity == null) {
            entity = new IdGenerater();
            entity.setEntityName(entityType);
            entity.setLastId("0");  // Starting from 0 if no records exist
        }

        // Get the last used ID for this entity (e.g., STAFF001, STAFF002, ...)
        int lastIdInt = Integer.parseInt(entity.getLastId());

        // Increment the ID (no need to hard-code, just continue from the highest ID)
        lastIdInt++;

        // Update the lastId and save to the database
        entity.setLastId(String.valueOf(lastIdInt));
        idGeneraterDAO.save(entity);

        // Return the generated ID in the format, e.g., STAFF001, FIELD001, etc.
        return entityType + String.format("%03d", lastIdInt);
    }

    public static String profilePicToBase64(byte[] profilePic) {
        return java.util.Base64.getEncoder().encodeToString(profilePic);
    }
}
