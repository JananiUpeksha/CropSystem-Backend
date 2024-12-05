package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);

}
