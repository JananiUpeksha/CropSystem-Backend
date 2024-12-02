package lk.ijse.cropmanagement.dao;

import lk.ijse.cropmanagement.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldDAO extends JpaRepository<FieldEntity,String> {
    @Query("SELECT f FROM FieldEntity f LEFT JOIN FETCH f.staffMembers WHERE f.FieldId = :fieldId")
    Optional<FieldEntity> findByFieldIdWithStaff(@Param("fieldId") String fieldId);
}
