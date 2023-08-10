package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.InvoiceDTO;
import com.laptrinhjavaweb.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter {
    public InvoiceDTO toDto(InvoiceEntity entity) {
        InvoiceDTO result = new InvoiceDTO();
        result.setId(entity.getId());
        result.setDiscount(entity.getDiscount());
        result.setAmount(entity.getAmount());
        result.setPartnerId(entity.getPartner().getId());
        return result;
    }

    public InvoiceEntity toEntity(InvoiceDTO dto) {
        InvoiceEntity result = new InvoiceEntity();
        result.setDiscount(dto.getDiscount());
        result.setAmount(dto.getAmount());
        return result;
    }

    public InvoiceEntity toEntity(InvoiceEntity result, InvoiceDTO dto) {
        result.setDiscount(dto.getDiscount());
        result.setAmount(dto.getAmount());
        return result;
    }
}
