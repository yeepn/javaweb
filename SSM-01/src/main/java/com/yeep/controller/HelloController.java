package com.yeep.controller;

import com.yeep.pojo.User;
import com.yeep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @GetMapping("hello/{id}")
    public String hello(@PathVariable String id, Model model){
        User userByid = userService.getUserByid(3);
        model.addAttribute("msg",userByid);
        return "hello";
    }
}
