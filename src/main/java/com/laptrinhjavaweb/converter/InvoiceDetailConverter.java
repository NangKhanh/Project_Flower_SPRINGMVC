package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.InvoiceDetailDTO;
import com.laptrinhjavaweb.entity.FlowerEntity;
import com.laptrinhjavaweb.entity.InvoiceDetailEntity;
import com.laptrinhjavaweb.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDetailConverter {
    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private FlowerConverter flowerConverter;

    public InvoiceDetailDTO toDto(InvoiceDetailEntity entity) {
        InvoiceDetailDTO result = new InvoiceDetailDTO();
        result.setId(entity.getId());
        result.setQuantity(entity.getQuantity());
        result.setTotalPrice(entity.getTotalPrice());
        result.setInvoiceId(entity.getInvoice().getId());
        result.setFlowerId(entity.getFlower().getId());
        FlowerEntity flowerEntity = flowerRepository.findOne(entity.getFlower().getId());
        result.setFlower(flowerConverter.toDto(flowerEntity));
        return result;
    }

    public InvoiceDetailEntity toEntity(InvoiceDetailDTO dto) {
        InvoiceDetailEntity result = new InvoiceDetailEntity();
        result.setQuantity(dto.getQuantity());
        result.setTotalPrice(dto.getTotalPrice());
        return result;
    }

    public InvoiceDetailEntity toEntity(InvoiceDetailEntity result, InvoiceDetailDTO dto) {
        result.setQuantity(dto.getQuantity());
        result.setTotalPrice(dto.getTotalPrice());
        return result;
    }
}
