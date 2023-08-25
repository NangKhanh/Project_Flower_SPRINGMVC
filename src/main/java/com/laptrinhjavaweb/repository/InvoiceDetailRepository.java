package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.FlowerEntity;
import com.laptrinhjavaweb.entity.InvoiceDetailEntity;
import com.laptrinhjavaweb.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetailEntity, Long> {
    List<InvoiceDetailEntity> findByInvoice(InvoiceEntity invoice);
    InvoiceDetailEntity findByInvoiceAndFlower(InvoiceEntity invoice, FlowerEntity flower);
}
