package lk.ijse.cropmanagement.utill;

import lk.ijse.cropmanagement.dto.impl.*;
import lk.ijse.cropmanagement.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    // Mapping methods for User
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> asUserDTOList(List<UserEntity> userEntityList) {
        return modelMapper.map(userEntityList, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    // Mapping methods for Staff
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public List<StaffDTO> asStaffDTOList(List<StaffEntity> staffEntityList) {
        return modelMapper.map(staffEntityList, new TypeToken<List<StaffDTO>>() {
        }.getType());
    }

    // Mapping methods for Vehicle
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    public List<VehicleDTO> asVehicleDTOList(List<VehicleEntity> vehicleEntityList) {
        return modelMapper.map(vehicleEntityList, new TypeToken<List<VehicleDTO>>() {
        }.getType());
    }

    // Mapping methods for Equipment
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }

    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
    }

    public List<EquipmentDTO> asEquipmentDTOList(List<EquipmentEntity> equipmentEntityList) {
        return modelMapper.map(equipmentEntityList, new TypeToken<List<EquipmentDTO>>() {
        }.getType());
    }

    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }
    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }

    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldEntityList) {
        return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDTO>>() {}.getType());
    }
}

