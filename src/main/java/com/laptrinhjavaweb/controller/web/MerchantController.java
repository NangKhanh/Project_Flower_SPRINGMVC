package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.dto.PartnerDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IMerchantService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "merchantController")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private IUserService userService;

    @GetMapping("/merchant/danh-sach")
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit, HttpServletRequest request){

        PartnerDTO model = new PartnerDTO();
        model.setPage(page);
        model.setLimit(limit);

        ModelAndView mav = new ModelAndView("web/partner/merchant/list");

        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(merchantService.findAll(pageable));
        model.setTotalItem(merchantService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        setMessageAndAlert(request, mav, model);
        mav.addObject("model", model);
        return mav;
    }

    @GetMapping ("/merchant/chinh-sua")
    public ModelAndView editMerchant(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/partner/merchant/edit");
        PartnerDTO model = new PartnerDTO();
        if(id != null){
            model = merchantService.findById(id);
        }
        setMessageAndAlert(request, mav, model);
        return mav;
    }

    private void setMessageAndAlert(HttpServletRequest request, ModelAndView mav, PartnerDTO model) {
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model", model);
    }

    @PostMapping("/merchant/chinh-sua")
    @ResponseBody
    public PartnerDTO createMerchant(@RequestBody PartnerDTO partnerDTO){

        PartnerDTO result = merchantService.save(partnerDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setGroupId(3L);
        userDTO.setPartnerId(result.getId());
        userDTO.setFullName(result.getName());
        userDTO.setUserName(result.getUsername());
        userDTO.setFirstLogin(true);
        userService.save(userDTO);
        return result;
    }

    @PutMapping("/merchant/chinh-sua")
    @ResponseBody
    public PartnerDTO updateMerchant(@RequestBody PartnerDTO partnerDTO){
        return merchantService.save(partnerDTO);
    }

    @DeleteMapping ("/merchant/chinh-sua")
    @ResponseBody
    public void deleteMerchant(@RequestBody long[] ids){
        merchantService.delete(ids);
    }
}
