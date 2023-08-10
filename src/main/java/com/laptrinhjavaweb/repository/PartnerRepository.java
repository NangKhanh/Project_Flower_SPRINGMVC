package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.PartnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
    Page<PartnerEntity> findByTypeAndStatus(String type, int status, Pageable pageable);
    int countByTypeAndStatus(String type, int status);
}
