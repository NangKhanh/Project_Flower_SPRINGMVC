package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.InvoiceConverter;
import com.laptrinhjavaweb.dto.InvoiceDTO;
import com.laptrinhjavaweb.dto.PartnerDTO;
import com.laptrinhjavaweb.entity.InvoiceEntity;
import com.laptrinhjavaweb.entity.PartnerEntity;
import com.laptrinhjavaweb.repository.InvoiceRepository;
import com.laptrinhjavaweb.repository.PartnerRepository;
import com.laptrinhjavaweb.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceConverter invoiceConverter;
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<InvoiceDTO> findAll(Long partnerId, Pageable pageable) {
        List<InvoiceDTO> invoiceModels = new ArrayList<>();
        PartnerEntity partner = partnerRepository.findOne(partnerId);
        Page<InvoiceEntity> invoicePage = invoiceRepository.findByPartnerAndStatus(partner,1, pageable);
        List<InvoiceEntity> entities = invoicePage.getContent();
        for (InvoiceEntity item : entities) {
            InvoiceDTO invoiceDTO = invoiceConverter.toDto(item);
            invoiceModels.add(invoiceDTO);
        }
        return invoiceModels;
    }

    @Override
    public int getTotalItem(Long partnerId) {
        PartnerEntity partner = partnerRepository.findOne(partnerId);
        return invoiceRepository.countByPartnerAndStatus(partner, 1);
    }

    @Override
    public InvoiceDTO findById(long id) {
        return invoiceConverter.toDto(invoiceRepository.findOne(id));
    }

    @Override
    public InvoiceDTO save(InvoiceDTO dto) {
        InvoiceEntity invoiceEntity;
        if(dto.getId() != null){
            InvoiceEntity oldInvoice = invoiceRepository.findOne(dto.getId());
            invoiceEntity = invoiceConverter.toEntity(oldInvoice, dto);
        }else{
            invoiceEntity = invoiceConverter.toEntity(dto);
        }
        invoiceEntity.setPartner(partnerRepository.findOne(dto.getPartnerId()));
        invoiceEntity.setStatus(1);
        return invoiceConverter.toDto(invoiceRepository.save(invoiceEntity));
    }

    @Override
    public void delete(long[] ids) {
        for(long id : ids){
            InvoiceEntity entity = invoiceRepository.findOne(id);
            entity.setStatus(0);
            invoiceRepository.save(entity);
        }
    }

}
