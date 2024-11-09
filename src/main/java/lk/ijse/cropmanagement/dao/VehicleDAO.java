package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDAO extends JpaRepository<VehicleEntity,String> {
}
