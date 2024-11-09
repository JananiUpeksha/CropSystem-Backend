package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDAO extends JpaRepository<StaffEntity,String> {
}
