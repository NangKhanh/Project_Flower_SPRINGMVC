package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.converter.FlowerConverter;
import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.entity.FlowerEntity;
import com.laptrinhjavaweb.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.service.IFlowerService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlowerService implements IFlowerService {

	@Autowired
	private FlowerRepository flowerRepository;
	@Autowired
	private FlowerConverter flowerConverter;

	@Override
	public List<FlowerDTO> findAll(Pageable pageable) {
		List<FlowerDTO> flowerModels = new ArrayList<>();
		List<FlowerEntity> entities = flowerRepository.findAll(pageable).getContent();
		for (FlowerEntity item : entities) {
			FlowerDTO flowerDTO = flowerConverter.toDto(item);
			flowerModels.add(flowerDTO);
		}
		return flowerModels;
	}

	@Override
	public int getTotalItem() {
		return (int) flowerRepository.count();
	}

	@Override
	public FlowerDTO findById(long id) {
		FlowerEntity entity = flowerRepository.findOne(id);
		return flowerConverter.toDto(entity);
	}

	@Override
	@Transactional
	public FlowerDTO save(FlowerDTO dto) {
		FlowerEntity flowerEntity = new FlowerEntity();
		if (dto.getId() != null) {
			FlowerEntity oldFlower = flowerRepository.findOne(dto.getId());
			flowerEntity = flowerConverter.toEntity(oldFlower, dto);
		} else {
			flowerEntity = flowerConverter.toEntity(dto);
		}
		return flowerConverter.toDto(flowerRepository.save(flowerEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id: ids){
			flowerRepository.delete(id);
		}
	}

	@Override
	public List<FlowerDTO> searchFlowers(String keyword) {
		List<FlowerDTO> dtos = new ArrayList<>();
		List<FlowerEntity> entities = flowerRepository.findByNameContaining(keyword);
		for (FlowerEntity item : entities){
			dtos.add(flowerConverter.toDto(item));
		}
		return dtos;
	}
}