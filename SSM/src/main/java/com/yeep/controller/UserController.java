package com.yeep.controller;

import com.yeep.pojo.User;
import com.yeep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @GetMapping("/hello/{id}")
    public String getUserById(@PathVariable String id, Model model){
        User user = userService.getUserById(Integer.valueOf(id));
        model.addAttribute("msg",user);
        return "hello";
    }

}
