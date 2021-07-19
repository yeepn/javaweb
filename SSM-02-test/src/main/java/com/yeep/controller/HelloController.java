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

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @RequestMapping("/getUserById/{id}")
    public String getUserById( @PathVariable(name = "id") String id, Model model){
        User userById = userService.getUserById(Integer.valueOf(id));
        model.addAttribute("msg",userById);
        return "hello";
    }
    @RequestMapping("alluser")
    public String getAllUsers(Model model){
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("msg",allUsers);
        return "alluser";
    }
}
