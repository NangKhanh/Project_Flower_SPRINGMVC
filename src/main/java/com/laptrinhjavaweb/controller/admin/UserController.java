package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.GroupDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IGroupService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "userController")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private MessageUtil messageUtil;
    private SystemConstant constant;
    @GetMapping(value = "/quan-tri/nguoi-dung/danh-sach")
    public ModelAndView listUser( HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/list");
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        UserDTO model = new UserDTO();
        model.setListResult(userService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @GetMapping(value = "/quan-tri/nguoi-dung/chinh-sua")
    public ModelAndView editUser(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/user/edit");
        GroupDTO group = new GroupDTO();
        UserDTO model = new UserDTO();
        group.setListResult(groupService.findAll());
        if(id != null){
            model = userService.findById(id);
        }
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("group", group);
        mav.addObject("model", model);

        return mav;
    }
//modelAttribute="model"  @ModelAttribute("model") UserDTO user
    @PostMapping(value = "/quan-tri/nguoi-dung/chinh-sua")
    public ModelAndView updateUser(@ModelAttribute("model") UserDTO user){
        String message = "insert_success";
        if(user.getId() != null){
            message = "update_success";
        }
        userService.save(user);
        return new ModelAndView("redirect:/quan-tri/nguoi-dung/danh-sach?message="+message);
    }

    @GetMapping(value = "/quan-tri/nguoi-dung/chinh-sua/doi-trang-thai")
    public ModelAndView changeStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "status") int status){
        userService.changeStatus(id, status);
        return new ModelAndView("redirect:/quan-tri/nguoi-dung/danh-sach?message=update_success");
    }
}
