package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerEntity, Long> {
	
}
