package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.FlowerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFlowerService {
	List<FlowerDTO> findAll(Pageable pageable);
	int getTotalItem();
	FlowerDTO findById(long id);
	FlowerDTO save(FlowerDTO dto);
	void delete(long[] ids);
	List<FlowerDTO> searchFlowers(String keyword);
}
