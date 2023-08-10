package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.PartnerConverter;
import com.laptrinhjavaweb.dto.PartnerDTO;
import com.laptrinhjavaweb.entity.PartnerEntity;
import com.laptrinhjavaweb.repository.PartnerRepository;
import com.laptrinhjavaweb.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PartnerConverter partnerConverter;
    public static final String MERCHANT = "merchant";

    @Override
    public List<PartnerDTO> findAll(Pageable pageable) {
        List<PartnerDTO> merchantModels = new ArrayList<>();
        Page<PartnerEntity> partnerPage = partnerRepository.findByTypeAndStatus(MERCHANT, 1, pageable);
        List<PartnerEntity> entities = partnerPage.getContent();
        for (PartnerEntity item : entities) {
            PartnerDTO partnerDTO = partnerConverter.toDto(item);
            merchantModels.add(partnerDTO);
        }
        return merchantModels;
    }

    @Override
    public int getTotalItem() {
        return partnerRepository.countByTypeAndStatus(MERCHANT, 1);
    }

    @Override
    public PartnerDTO findById(long id) {
        PartnerEntity entity = partnerRepository.findOne(id);
        return partnerConverter.toDto(entity);
    }

    @Override
    public PartnerDTO save(PartnerDTO dto) {
        PartnerEntity merchantEntity;
        if (dto.getId() != null) {
            PartnerEntity oldMerchant = partnerRepository.findOne(dto.getId());
            merchantEntity = partnerConverter.toEntity(oldMerchant, dto);
        } else {
            merchantEntity = partnerConverter.toEntity(dto);
        }
        return partnerConverter.toDto(partnerRepository.save(merchantEntity));
    }

    @Override
    public void delete(long[] ids) {
        for(long id : ids){
            PartnerEntity entity = partnerRepository.findOne(id);
            entity.setStatus(0);
            partnerRepository.save(entity);
        }
    }
}
