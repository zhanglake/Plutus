package com.plutus.controller;

import com.plutus.entity.User;
import com.plutus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zack.zhang on 2017/5/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        String name = "admin";
        String password = "111111";
        User user = userService.findByUserNameAndPassword(name, password);
        if (null == user) {
            return "login_failed";
        }
        return "showUser";
    }

}
