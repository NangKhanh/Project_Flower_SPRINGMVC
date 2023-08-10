package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.GroupEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserCoverter {
    public UserDTO toDto(UserEntity userEntity){
        UserDTO result = new UserDTO();
        result.setId((userEntity.getId()));
        result.setUserName(userEntity.getUserName());
        result.setStatus(userEntity.getStatus());
        result.setGroupName(userEntity.getGroupEntity().getGroupName());
        result.setGroupId(userEntity.getGroupEntity().getId());
        result.setFullName(userEntity.getFullName());
        return result;
    }

    public UserEntity toEntity(UserEntity userEntity, UserDTO userDTO){
        userEntity.setFullName(userDTO.getFullName());
        return userEntity;
    }

    public UserEntity toEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setStatus(1);
        userEntity.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10)));
        return userEntity;
    }
}
