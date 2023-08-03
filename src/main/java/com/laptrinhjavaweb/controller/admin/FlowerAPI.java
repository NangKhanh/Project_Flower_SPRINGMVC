package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.service.IFlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowerAPI {
    @Autowired
    private IFlowerService flowerService;

    @PostMapping(value = "/api/new")
    public FlowerDTO createFlower(@RequestBody FlowerDTO flowerDTO){
        return flowerService.save(flowerDTO);
    }

}
