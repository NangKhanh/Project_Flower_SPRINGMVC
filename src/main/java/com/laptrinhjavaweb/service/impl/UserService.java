package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.UserCoverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.GroupEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.GroupRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCoverter userCoverter;

    @Autowired
    private GroupRepository groupRepository;

    private SystemConstant constant;
    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userModel = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll();
        for (UserEntity userEntity :entities){
            UserDTO userDTO = userCoverter.toDto(userEntity);
            userModel.add(userDTO);
        }
        return userModel;
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity entity = userRepository.findOne(id);
        return userCoverter.toDto(entity);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        GroupEntity group = groupRepository.findOne(dto.getGroupId());
        UserEntity userEntity = new UserEntity();
        if(dto.getId() != null){
            UserEntity oldUserData = userRepository.findOne(dto.getId());
            oldUserData.setGroupEntity(group);
            userEntity = userCoverter.toEntity(oldUserData,dto);
        }else {
            userEntity = userCoverter.toEntity(dto);
            userEntity.setGroupEntity(group);
        }
        return userCoverter.toDto(userRepository.save(userEntity));
    }

    @Override
    public UserDTO changeStatus(Long id, int status) {
        UserEntity userEntity = userRepository.findOne(id);
        if(status == constant.ACTIVE_STATUS){
            status = constant.INACTIVE_STATUS;
        }else {
            status = constant.ACTIVE_STATUS;
        }
        userEntity.setStatus(status);
        return userCoverter.toDto(userRepository.save(userEntity));
    }
}
