package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.dto.InvoiceDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceDTO> findAll(Long partnerId, Pageable pageable);
    int getTotalItem(Long partnerId);
    InvoiceDTO findById(long id);
    InvoiceDTO save(InvoiceDTO dto);
    void delete(long[] ids);
}
