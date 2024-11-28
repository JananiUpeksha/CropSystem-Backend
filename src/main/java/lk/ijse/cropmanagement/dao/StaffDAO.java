package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffDAO extends JpaRepository<StaffEntity,String> {

    Optional<StaffEntity> findByEmail(String email);
}
