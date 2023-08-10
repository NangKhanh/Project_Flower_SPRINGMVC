package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.PartnerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMerchantService {
    List<PartnerDTO> findAll(Pageable pageable);
    int getTotalItem();
    PartnerDTO findById(long id);
    PartnerDTO save(PartnerDTO dto);
    void delete(long[] ids);
}
