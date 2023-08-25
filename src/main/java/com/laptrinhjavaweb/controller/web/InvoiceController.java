package com.laptrinhjavaweb.controller.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.dto.InvoiceDTO;
import com.laptrinhjavaweb.dto.InvoiceDetailDTO;
import com.laptrinhjavaweb.service.IFlowerService;
import com.laptrinhjavaweb.service.IInvoiceDetailService;
import com.laptrinhjavaweb.service.IInvoiceService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IInvoiceDetailService invoiceDetailService;

    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private IFlowerService flowerService;

    @GetMapping("/hoa-don/danh-sach")
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("partnerId") Long partnerId,
                                 @RequestParam("limit") int limit, HttpServletRequest request){

        InvoiceDTO model = new InvoiceDTO();
        model.setPage(page);
        model.setLimit(limit);
        model.setPartnerId(partnerId);

        ModelAndView mav = new ModelAndView("web/invoice/list");

        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(invoiceService.findAll(partnerId, pageable));
        model.setTotalItem(invoiceService.getTotalItem(partnerId));
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        setMessageAndAlert(request, mav, model);
        return mav;
    }

    @GetMapping ("/hoa-don/chinh-sua")
    public ModelAndView editFlower(@RequestParam(value = "id", required = false) Long id,
                                   @RequestParam("partnerId") Long partnerId,
                                   HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/invoice/edit");
        InvoiceDTO invoiceModel = new InvoiceDTO();
        InvoiceDetailDTO invoiceDetailModel = new InvoiceDetailDTO();
        invoiceModel.setPartnerId(partnerId);
        if(id != null){
            invoiceModel = invoiceService.findById(id);
            invoiceDetailModel.setListResult(invoiceDetailService.findAll(id));
        }
        setMessageAndAlert(request, mav, invoiceModel);
        mav.addObject("detailModel", invoiceDetailModel);
        return mav;
    }

    private void setMessageAndAlert(HttpServletRequest request, ModelAndView mav, InvoiceDTO model) {
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model", model);
    }

    @PostMapping("/hoa-don/chinh-sua")
    @ResponseBody
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.save(invoiceDTO);
    }

    @PutMapping("/hoa-don/chinh-sua")
    @ResponseBody
    public InvoiceDTO updateInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        List<Long> listIds = new ArrayList<>();

        for(InvoiceDetailDTO invoiceDetailDTO: invoiceDTO.getInvoiceDetails()){
//            Lấy giá trị cũ và giá trị tồn kho cũ
            InvoiceDetailDTO oldDetail = invoiceDetailService.findOne(invoiceDTO.getId(), invoiceDetailDTO.getFlowerId());

            if(oldDetail != null){
                FlowerDTO flowerDTO = flowerService.findById(invoiceDetailDTO.getFlowerId());
//            update tồn kho
                int quantity = flowerDTO.getQuantity() - invoiceDetailDTO.getQuantity() + oldDetail.getQuantity();
                flowerDTO.setQuantity(quantity);
                flowerService.save(flowerDTO);
            }
            invoiceDetailDTO.setInvoiceId(invoiceDTO.getId());

            listIds.add(invoiceDetailService.save(invoiceDetailDTO).getId());
        }

        List<InvoiceDetailDTO> list = invoiceDetailService.findAll(invoiceDTO.getId());
        if(list != null){
            for(InvoiceDetailDTO item: list){
                if(!listIds.contains(item.getId())){
                    invoiceDetailService.delete(item.getId());
                }
            }
        }
        return invoiceService.save(invoiceDTO);
    }

    @DeleteMapping ("/hoa-don/chinh-sua")
    @ResponseBody
    public void deleteInvoice(@RequestBody long[] ids){
        invoiceService.delete(ids);
    }
}
