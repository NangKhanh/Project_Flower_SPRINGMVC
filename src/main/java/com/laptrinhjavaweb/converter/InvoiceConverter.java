package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.InvoiceDTO;
import com.laptrinhjavaweb.dto.PartnerDTO;
import com.laptrinhjavaweb.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter {
    public InvoiceDTO toDto(InvoiceEntity entity) {
        InvoiceDTO result = new InvoiceDTO();
        result.setId(entity.getId());
        result.setDiscount(entity.getDiscount());
        result.setTotalCost(entity.getTotalCost());
        result.setPartnerId(entity.getPartner().getId());
        return result;
    }

    public InvoiceEntity toEntity(InvoiceDTO dto) {
        InvoiceEntity result = new InvoiceEntity();
        result.setDiscount(dto.getDiscount());
        result.setTotalCost(dto.getTotalCost());
        return result;
    }

    public InvoiceEntity toEntity(InvoiceEntity result, InvoiceDTO dto) {
        result.setDiscount(dto.getDiscount());
        result.setTotalCost(dto.getTotalCost());
        return result;
    }
}
