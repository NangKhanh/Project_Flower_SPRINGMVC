package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository extends JpaRepository<FlowerEntity, Long> {
	List<FlowerEntity> findByNameContaining(String keyword);
}
