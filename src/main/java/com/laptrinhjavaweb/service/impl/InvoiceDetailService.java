package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.InvoiceDetailConverter;
import com.laptrinhjavaweb.dto.InvoiceDTO;
import com.laptrinhjavaweb.dto.InvoiceDetailDTO;
import com.laptrinhjavaweb.entity.InvoiceDetailEntity;
import com.laptrinhjavaweb.entity.InvoiceEntity;
import com.laptrinhjavaweb.entity.PartnerEntity;
import com.laptrinhjavaweb.repository.FlowerRepository;
import com.laptrinhjavaweb.repository.InvoiceDetailRepository;
import com.laptrinhjavaweb.repository.InvoiceRepository;
import com.laptrinhjavaweb.service.IInvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceDetailService implements IInvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository detailRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailConverter detailConverter;
    @Autowired
    private FlowerRepository flowerRepository;

    @Override
    public List<InvoiceDetailDTO> findAll(Long invoiceId) {
        List<InvoiceDetailDTO> detailModels = new ArrayList<>();
        InvoiceEntity invoice = invoiceRepository.findOne(invoiceId);
        List<InvoiceDetailEntity> detailEntities = detailRepository.findByInvoice(invoice);
        for (InvoiceDetailEntity item : detailEntities) {
            InvoiceDetailDTO detailDTO = detailConverter.toDto(item);
            detailModels.add(detailDTO);
        }
        return detailModels;
    }

    @Override
    public InvoiceDetailDTO findOne(Long invoiceId, Long flowerId) {
        InvoiceDetailEntity entity = detailRepository.findByInvoiceAndFlower(invoiceRepository.findOne(invoiceId) ,
                flowerRepository.findOne(flowerId));
        return detailConverter.toDto(entity);
    }


    @Override
    public InvoiceDetailDTO save(InvoiceDetailDTO dto) {
        InvoiceDetailEntity invoiceDetailEntity;
        if(dto.getId() != null){
            InvoiceDetailEntity oldDetail = detailRepository.findOne(dto.getId());
            invoiceDetailEntity = detailConverter.toEntity(oldDetail, dto);
        }else{
            invoiceDetailEntity = detailConverter.toEntity(dto);
        }
        invoiceDetailEntity.setFlower(flowerRepository.findOne(dto.getFlowerId()));
        invoiceDetailEntity.setInvoice(invoiceRepository.findOne(dto.getInvoiceId()));
        return detailConverter.toDto(detailRepository.save(invoiceDetailEntity));
    }

    @Override
    public void delete(long id) {
        detailRepository.delete(id);
    }
}
