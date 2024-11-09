package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserEntity,String> {
}
