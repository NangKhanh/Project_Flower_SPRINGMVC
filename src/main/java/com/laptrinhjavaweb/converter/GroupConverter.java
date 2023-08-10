package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.GroupDTO;
import com.laptrinhjavaweb.entity.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter {
    public GroupDTO toDto(GroupEntity groupEntity) {
        GroupDTO result = new GroupDTO();
        result.setId(groupEntity.getId());
        result.setGroupName(groupEntity.getGroupName());
        return result;
    }
}
