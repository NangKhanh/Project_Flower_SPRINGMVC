package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.InvoiceDetailDTO;

import java.util.List;

public interface IInvoiceDetailService {
    List<InvoiceDetailDTO> findAll(Long invoiceId);
    InvoiceDetailDTO findOne(Long invoiceId, Long flowerId);
    InvoiceDetailDTO save(InvoiceDetailDTO dto);
    void delete(long id);
}
