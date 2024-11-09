package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDAO extends JpaRepository<EquipmentEntity,String> {
}
