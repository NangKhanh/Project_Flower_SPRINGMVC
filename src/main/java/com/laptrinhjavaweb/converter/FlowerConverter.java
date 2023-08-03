package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.entity.FlowerEntity;
import org.springframework.stereotype.Component;

@Component
public class FlowerConverter {

    public FlowerDTO toDto(FlowerEntity entity) {
        FlowerDTO result = new FlowerDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());
        result.setPrice(entity.getPrice());
        result.setQuantity(entity.getQuantity());
        return result;
    }

    public FlowerEntity toEntity(FlowerDTO dto) {
        FlowerEntity result = new FlowerEntity();
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        result.setPrice(dto.getPrice());
        result.setQuantity(dto.getQuantity());
        return result;
    }

    public FlowerEntity toEntity(FlowerEntity result, FlowerDTO dto) {
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        result.setPrice(dto.getPrice());
        result.setQuantity(dto.getQuantity());
        return result;
    }
}