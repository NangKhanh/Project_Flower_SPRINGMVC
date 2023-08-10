package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.dto.PartnerDTO;
import com.laptrinhjavaweb.entity.FlowerEntity;
import com.laptrinhjavaweb.entity.PartnerEntity;
import org.springframework.stereotype.Component;

@Component
public class PartnerConverter {
    public PartnerDTO toDto(PartnerEntity entity) {
        PartnerDTO result = new PartnerDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setUsername(entity.getUsername());
        result.setAddress(entity.getAddress());
        result.setPhone(entity.getPhone());
        result.setDiscountRate(entity.getDiscountRate());
        result.setType(entity.getType());
        result.setStatus(entity.getStatus());
        return result;
    }

    public PartnerEntity toEntity(PartnerDTO dto) {
        PartnerEntity result = new PartnerEntity();
        result.setName(dto.getName());
        result.setUsername(dto.getUsername());
        result.setAddress(dto.getAddress());
        result.setPhone(dto.getPhone());
        result.setDiscountRate(dto.getDiscountRate());
        result.setType(dto.getType());
        result.setStatus(dto.getStatus());
        return result;
    }

    public PartnerEntity toEntity(PartnerEntity result,PartnerDTO dto) {
        result.setName(dto.getName());
        result.setUsername(dto.getUsername());
        result.setAddress(dto.getAddress());
        result.setPhone(dto.getPhone());
        result.setDiscountRate(dto.getDiscountRate());
        result.setType(dto.getType());
        result.setStatus(dto.getStatus());
        return result;
    }
}
