package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDAO extends JpaRepository<CropEntity,String> {
}
