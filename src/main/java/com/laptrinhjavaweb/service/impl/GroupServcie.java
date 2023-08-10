package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.GroupConverter;
import com.laptrinhjavaweb.dto.GroupDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.GroupEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.GroupRepository;
import com.laptrinhjavaweb.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServcie implements IGroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupConverter groupConverter;

    @Override
    public List<GroupDTO> findAll() {
        List<GroupDTO> groupModel = new ArrayList<>();
        List<GroupEntity> entities = groupRepository.findAll();
        for (GroupEntity groupEntity :entities){
            GroupDTO groupDTO = groupConverter.toDto(groupEntity);
            groupModel.add(groupDTO);
        }
        return groupModel;
    }
}
