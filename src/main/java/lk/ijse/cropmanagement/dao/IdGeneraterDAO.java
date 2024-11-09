package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.IdGenerater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdGeneraterDAO extends JpaRepository<IdGenerater, String> {
    // Fetch the IdGenerater entity based on the entityName
    IdGenerater findByEntityName(String entityName);
}
