package com.plutus.controller;

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

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        return "showUser";
    }
}
