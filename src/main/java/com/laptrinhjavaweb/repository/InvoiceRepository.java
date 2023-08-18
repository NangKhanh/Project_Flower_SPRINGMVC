package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.InvoiceEntity;
import com.laptrinhjavaweb.entity.PartnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    Page<InvoiceEntity> findByPartnerAndStatus(PartnerEntity partner, int status, Pageable pageable);
    int countByPartnerAndStatus(PartnerEntity partner, int status);
}
