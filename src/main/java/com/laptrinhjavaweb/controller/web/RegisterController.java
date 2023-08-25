package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.converter.UserCoverter;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCoverter userCoverter;

    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @PostMapping("dang-ky")
    @ResponseBody
    public void registerUser(@RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "password", required = false) String password){
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, 1);
        if(userEntity == null){
            String message = "Username không tồn tại!";
            String alert = "alert";
        }else{
            userService.save(userCoverter.toDto(userEntity));
        }
    }
}
