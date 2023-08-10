package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.GroupDTO;

import java.util.List;

public interface IGroupService {
    List<GroupDTO> findAll();
}
