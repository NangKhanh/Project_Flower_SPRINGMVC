package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO dto);

    UserDTO changeStatus(Long id, int status);
}
