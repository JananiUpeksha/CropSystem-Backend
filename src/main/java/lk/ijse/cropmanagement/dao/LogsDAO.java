package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsDAO extends JpaRepository<LogEntity,String> {
}
