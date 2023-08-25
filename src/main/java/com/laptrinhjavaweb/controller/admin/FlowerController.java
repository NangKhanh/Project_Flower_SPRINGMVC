package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.service.IFlowerService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "flowerController")

public class FlowerController {

    @Autowired
    private IFlowerService flowerService;

    @Autowired
    private MessageUtil messageUtil;

    @GetMapping("/quan-tri/hoa/danh-sach")
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit, HttpServletRequest request){

        FlowerDTO model = new FlowerDTO();
        model.setPage(page);
        model.setLimit(limit);

        ModelAndView mav = new ModelAndView("admin/flower/list");

        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(flowerService.findAll(pageable));
        model.setTotalItem(flowerService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        setMessageAndAlert(request, mav, model);
//        mav.addObject("model", model);
        return mav;
    }

    @GetMapping ("/quan-tri/hoa/chinh-sua")
    public ModelAndView editFlower(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/flower/edit");
        FlowerDTO model = new FlowerDTO();
        if(id != null){
            model = flowerService.findById(id);
        }
        setMessageAndAlert(request, mav, model);
        return mav;
    }

    private void setMessageAndAlert(HttpServletRequest request, ModelAndView mav, FlowerDTO model) {
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model", model);
    }

    @GetMapping(value = "tim-kiem-hoa", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FlowerDTO> searchFlower(@RequestParam(value = "keyword", required = false) String keyword){
        List<FlowerDTO> flowers = new ArrayList<>();
        if(keyword != null){
            flowers = flowerService.searchFlowers(keyword);
        }
        return flowers;
    }

    @PostMapping("/quan-tri/hoa/chinh-sua")
    @ResponseBody
    public FlowerDTO createFlower(@RequestBody FlowerDTO flowerDTO){
        return flowerService.save(flowerDTO);
    }

    @PutMapping("/quan-tri/hoa/chinh-sua")
    @ResponseBody
    public FlowerDTO updateFlower(@RequestBody FlowerDTO flowerDTO){
        return flowerService.save(flowerDTO);
    }

    @DeleteMapping ("/quan-tri/hoa/chinh-sua")
    @ResponseBody
    public void deleteFlower(@RequestBody long[] ids){
        flowerService.delete(ids);
    }
}
